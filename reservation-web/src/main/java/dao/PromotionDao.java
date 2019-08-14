package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dto.promotion.PromotionDto;
import dto.promotion.PromotionsDto;

@Repository
public class PromotionDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PromotionDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public PromotionsDto getPromotionList() {
		PromotionsDto promotionsDto = new PromotionsDto();
		
		List<PromotionDto> promotionList = jdbcTemplate.query(
				"SELECT promotion.id, promotion.product_id as productId, file_info.save_file_name as productImageUrl "
				+ "from ((promotion JOIN product ON promotion.product_id = product.id) "
				+ "JOIN product_image ON promotion.product_id = product_image.product_id AND product_image.type=\"th\") "
				+ "JOIN file_info ON product_image.file_id = file_info.id",
				new RowMapper<PromotionDto>() {
					@Override
					public PromotionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						PromotionDto promotionDto = new PromotionDto(rs.getInt("id"), rs.getInt("productId"),rs.getString("productImageUrl"));
						return promotionDto;
					}
				});
		
		promotionsDto.setItems(promotionList);
		
		return promotionsDto.isEmpty() ? null : promotionsDto;
	}
}
