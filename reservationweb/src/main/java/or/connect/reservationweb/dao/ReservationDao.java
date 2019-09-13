package or.connect.reservationweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import or.connect.reservationweb.dto.reservation.request.ReservationRequest;

@Repository
public class ReservationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ReservationInfoDto> getResrvationInfoList(String reservationEmail) {
		List<ReservationInfoDto> reservationInfoList = jdbcTemplate.query(
				"SELECT reservation_info.id AS reservationInfoId, product_id AS productId, display_info_id AS displayInfoId, "
						+ "reservation_name, reservation_tel, reservation_email, cancel_flag, reservation_date, create_date, modify_date "
						+ "FROM reservation_info " + "WHERE reservation_email = ?",
				new RowMapper<ReservationInfoDto>() {
					@Override
					public ReservationInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						ReservationInfoDto reservationInfoDto = new ReservationInfoDto();
						reservationInfoDto.setCancelYn(rs.getBoolean("cancel_flag"));
						reservationInfoDto.setCreateDate(rs.getString("create_date"));
						reservationInfoDto.setDisplayInfoId(rs.getInt("displayInfoId"));
						reservationInfoDto.setProductId(rs.getInt("productId"));
						reservationInfoDto.setReservationDate(rs.getString("reservation_date"));
						reservationInfoDto.setReservationInfoId(rs.getInt("reservationInfoId"));
						reservationInfoDto.setReservationName(rs.getString("reservation_name"));
						reservationInfoDto.setReservationTelephone(rs.getString("reservation_tel"));

						return reservationInfoDto;
					}
				}, reservationEmail);

		return reservationInfoList.isEmpty() ? null : reservationInfoList;

	}

	public int registerReservation(ReservationRequest reservationRequest) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, cancel_flag, create_date, modify_date) "
						+ "values (?,?,?,?,?,?,?,?,?)",new String[] {"ID"} );
				pstmt.setInt(1,  reservationRequest.getProductId());
				pstmt.setInt(2, reservationRequest.getDisplayInfoId());
				pstmt.setString(3, reservationRequest.getReservationName());
				pstmt.setString(4, reservationRequest.getReservationTelephone());
				pstmt.setString(5, reservationRequest.getReservationEmail());
				pstmt.setString(6, reservationRequest.getReservationYearMonthDay());
				pstmt.setBoolean(7, reservationRequest.getCancelYn());
				pstmt.setTimestamp(8, Timestamp.valueOf(reservationRequest.getCreateDate()));
				pstmt.setTimestamp(9, Timestamp.valueOf(reservationRequest.getModifyDate()));
				return pstmt;
			}
		},keyHolder);
		
		Number keyValue = keyHolder.getKey();
		return keyValue.intValue();
	}
}
