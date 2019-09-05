package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.reservation.ReservationInfoDto;

@Repository
public class ReservationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<ReservationInfoDto> getResrvationInfoList(String reservationEmail) {
				List<ReservationInfoDto> reservationInfoList = jdbcTemplate.query("SELECT reservation_info.id AS reservationInfoId, product_id AS productId, display_info_id AS displayInfoId, " + 
					"reservation_name, reservation_tel, reservation_email, cancel_flag, reservation_date, create_date, modify_date " + 
					"FROM reservation_info " + 
					"WHERE reservation_email = ?",
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
	
	
}
