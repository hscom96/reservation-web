package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationPriceDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationPriceDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getTotalPrice(int reservationInfoId) {
		List<Integer> totalPrice = jdbcTemplate.query(
				"select SUM(price*count) as totalPrice from reservation_info " + 
				"INNER JOIN reservation_info_price ON reservation_info_price.reservation_info_id = reservation_info.id " + 
				"INNER JOIN product_price ON product_price.id = reservation_info_price.product_price_id " + 
				"WHERE reservation_info_id = ? ",
				new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getInt("totalPrice");
					}
				}, reservationInfoId);

		return totalPrice.isEmpty() ? null : totalPrice.get(0);
	}
	 
	
}
