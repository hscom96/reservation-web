package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.productInfo.ProductPriceDto;

@Repository
public class ProductPriceDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductPriceDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<ProductPriceDto> getProductPrice(int displayInfoId) {
		List<ProductPriceDto> productPriceList = jdbcTemplate
				.query("SELECT product_price.id AS productPriceId, product.id AS productId, product_price.price_type_name, "
						+ "product_price.price, product_price.discount_rate, product_price.create_date, product_price.modify_date "
						+ "FROM product "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "INNER JOIN product_price ON product.id = product_price.product_id "
						+ "WHERE display_info.id = ? "
						+ "ORDER BY productPriceId desc"
						,
						new RowMapper<ProductPriceDto>(){
							@Override
							public ProductPriceDto mapRow(ResultSet rs, int rowNum) throws SQLException {
								ProductPriceDto productPriceDto = new ProductPriceDto();
								
								productPriceDto.setCreateDate(rs.getString("create_date"));
								productPriceDto.setDiscountRate(rs.getInt("discount_rate"));
								productPriceDto.setModifyDate(rs.getString("modify_date"));
								productPriceDto.setPrice(rs.getInt("price"));
								productPriceDto.setPriceTypeName(rs.getString("price_type_name"));
								productPriceDto.setProductId(rs.getInt("productId"));
								productPriceDto.setProductPriceId(rs.getInt("productPriceId"));
								
								return productPriceDto;
							}
						}, displayInfoId);

		return productPriceList.isEmpty() ? null : productPriceList;
	}
}
