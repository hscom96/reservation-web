package or.connect.reservationweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.reservation.ReservationInfoDto;
import or.connect.reservationweb.dto.reservation.request.ReservationInfoPriceDto;

@Repository
public class ReservationDao {

	private JdbcTemplate jdbcTemplate;
	private static final String reservationInfo_select = "SELECT reservation_info.id AS reservationInfoId, product_id AS productId, display_info_id AS displayInfoId, "
			+ "reservation_name, reservation_tel, reservation_email, cancel_flag, reservation_date, create_date, modify_date "
			+ "FROM reservation_info";

	@Autowired
	public ReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ReservationInfoDto> getReservationInfoList(String reservationEmail) {
		List<ReservationInfoDto> reservationInfoList = jdbcTemplate.query(
				reservationInfo_select+" WHERE reservation_email = ?",
				new reservationInfoMapper(), reservationEmail);

		return reservationInfoList.isEmpty() ? null : reservationInfoList;
	}

	public ReservationInfoDto getReservationInfo(int reservationId) {
		List<ReservationInfoDto> reservationInfoList = jdbcTemplate.query(
				reservationInfo_select+ " WHERE reservation_info.id = ?",
				new reservationInfoMapper(), reservationId);

		return reservationInfoList.isEmpty() ? null : reservationInfoList.get(0);
	}

	public int registerReservation(ReservationInfoPriceDto reservationDto) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, cancel_flag, create_date, modify_date) "
								+ "values (?,?,?,?,?,?,?,?,?)",
						new String[] { "ID" });
				pstmt.setInt(1, reservationDto.getProductId());
				pstmt.setInt(2, reservationDto.getDisplayInfoId());
				pstmt.setString(3, reservationDto.getReservationName());
				pstmt.setString(4, reservationDto.getReservationTelephone());
				pstmt.setString(5, reservationDto.getReservationEmail());
				pstmt.setString(6, reservationDto.getReservationYearMonthDay());
				pstmt.setBoolean(7, reservationDto.getCancelYn());
				pstmt.setTimestamp(8, Timestamp.valueOf(reservationDto.getCreateDate()));
				pstmt.setTimestamp(9, Timestamp.valueOf(reservationDto.getModifyDate()));
				return pstmt;
			}
		}, keyHolder);

		Number keyValue = keyHolder.getKey();
		return keyValue.intValue();
	}

	public void setCancelReservation(int reservationId) {
		jdbcTemplate.update("UPDATE  reservation_info SET cancel_flag= 1  WHERE id = ?", reservationId);
	}
	
	class reservationInfoMapper implements RowMapper<ReservationInfoDto> {
		@Override
		public ReservationInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReservationInfoDto reservationInfoDto = new ReservationInfoDto();
			reservationInfoDto.setCancelYn(rs.getBoolean("cancel_flag"));
			reservationInfoDto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			reservationInfoDto.setDisplayInfoId(rs.getInt("displayInfoId"));
			reservationInfoDto.setProductId(rs.getInt("productId"));
			reservationInfoDto.setReservationDate(rs.getTimestamp("reservation_date").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			reservationInfoDto.setReservationInfoId(rs.getInt("reservationInfoId"));
			reservationInfoDto.setReservationName(rs.getString("reservation_name"));
			reservationInfoDto.setReservationTelephone(rs.getString("reservation_tel"));
			reservationInfoDto.setReservationEmail(rs.getString("reservation_email"));
			reservationInfoDto.setModifyDate(rs.getTimestamp("modify_date").toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			return reservationInfoDto;
		}
	}
}
