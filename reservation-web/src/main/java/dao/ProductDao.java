package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import dto.product.ProductDto;
import dto.product.ProductListDto;

@Repository
public class ProductDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public ProductListDto getProductList(int categoryId, int start) {
		ProductListDto productListDto = new ProductListDto();
		int totalCount;
		
		List<ProductDto> productList = jdbcTemplate.query(
				"select display_info.id ,display_info.product_id, product.description, "
				+ "display_info.place_name, product.content, file_info.file_name from product "
				+ "INNER JOIN display_info ON product.id = display_info.product_id "
				+ "INNER JOIN product_image ON product.id = product_image.product_id "
				+ "INNER JOIN file_info ON product_image.file_id = file_info.id AND type=\"th\" AND category_id=? "
				+ "ORDER BY display_info.id LIMIT 4 OFFSET ?"
				, new RowMapper<ProductDto>() {
					@Override
				public ProductDto mapRow(ResultSet rs,int rowNum) throws SQLException {
					ProductDto productDto = new ProductDto(rs.getInt("id"), rs.getInt("product_id"), 
							rs.getString("description"), rs.getString("place_name"), rs.getString("content"), rs.getString("file_name")); 
					return productDto;
				}}, categoryId, start);
		
		productListDto.setProductList(productList);
		totalCount = getTotalCount(categoryId);
		productListDto.setTotalCount(totalCount);
		
		return productListDto.isEmpty() ? null : productListDto;
	}
	
	public Integer getTotalCount(int categoryId) {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM product INNER JOIN category ON  product.category_id = category.id AND category_id = ?",
				Integer.class, categoryId);
		return count;
	}
}
