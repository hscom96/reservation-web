package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.promotion.PromotionDto;

@Repository
public class PromotionDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PromotionDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<PromotionDto> getPromotionList() {

		List<PromotionDto> promotionList = jdbcTemplate.query(
				"SELECT promotion.id, promotion.product_id as productId, file_info.save_file_name as productImageUrl, product.description, display_info.place_name as placeName "
						+ "from promotion JOIN product ON promotion.product_id = product.id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "INNER JOIN product_image ON promotion.product_id = product_image.product_id AND product_image.type=\"th\" "
						+ "INNER JOIN file_info ON product_image.file_id = file_info.id ",
				new RowMapper<PromotionDto>() {
					@Override
					public PromotionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						PromotionDto promotionDto = new PromotionDto(rs.getInt("id"), rs.getInt("productId"),
								rs.getString("productImageUrl"), rs.getString("description"),
								rs.getString("placeName"));
						return promotionDto;
					}
				});

		return promotionList.isEmpty() ? null : promotionList;
	}
}
