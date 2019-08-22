package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.product.ProductDto;
import or.connect.reservationweb.dto.productInfo.DisplayInfoDto;
import or.connect.reservationweb.dto.productInfo.DisplayInfoImageDto;
import or.connect.reservationweb.dto.productInfo.ProductImageDto;

@Repository
public class ProductDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 지정한 categoryId의 상품목록 반환
	public List<ProductDto> getProductList(int categoryId, int start) {
		List<ProductDto> productList = jdbcTemplate
				.query("select display_info.id ,display_info.product_id, product.description, "
						+ "display_info.place_name, product.content, file_info.file_name from product "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "INNER JOIN product_image ON product.id = product_image.product_id "
						+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
						+ "WHERE type=\"th\" AND category_id=? "
						+ "ORDER BY display_info.id LIMIT 4 OFFSET ?", new RowMapperProduct(), categoryId, start);

		return productList.isEmpty() ? null : productList;
	}

	// 전체 상품목록 반환
	public List<ProductDto> getProductList(int start) {
		List<ProductDto> productList = jdbcTemplate
				.query("select display_info.id ,display_info.product_id, product.description, "
						+ "display_info.place_name, product.content, file_info.file_name from product "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "INNER JOIN product_image ON product.id = product_image.product_id "
						+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
						+ "WHERE type=\"th\" "
						+ "ORDER BY display_info.id LIMIT 4 OFFSET ?", new RowMapperProduct(), start);

		return productList.isEmpty() ? null : productList;
	}

	public class RowMapperProduct implements RowMapper<ProductDto> {
		@Override
		public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductDto productDto = new ProductDto(rs.getInt("id"), rs.getInt("product_id"),
					rs.getString("description"), rs.getString("place_name"), rs.getString("content"),
					rs.getString("file_name"));
			return productDto;
		}
	}
	
	// 지정 categoryId의 상품 총개수 반환
	public Integer getTotalCount(int categoryId) {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM product INNER JOIN category ON  product.category_id = category.id "
						+ "INNER JOIN  display_info ON product.id = display_info.product_id "
						+ "WHERE category_id = ?",
				Integer.class, categoryId);
		return count;
	}

	// 전체상품 총개수 반환
	public Integer getTotalCount() {
		Integer count = jdbcTemplate.queryForObject(
				"SELECT COUNT(*) FROM product INNER JOIN category ON  product.category_id = category.id "
						+ "INNER JOIN  display_info ON product.id = display_info.product_id",
				Integer.class);
		return count;
	}


}
