package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.productInfo.DisplayInfoDto;
import or.connect.reservationweb.dto.productInfo.DisplayInfoImageDto;
import or.connect.reservationweb.dto.productInfo.ProductImageDto;
import or.connect.reservationweb.dto.productInfo.ProductPriceDto;

@Repository
public class ProductDisplayDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductDisplayDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	public DisplayInfoDto getDisplayInfo(int displayInfoId){

		List<DisplayInfoDto> displayInfoList = jdbcTemplate
				.query("SELECT display_info.product_id AS productId, product.category_id AS categoryId, display_info.id AS displayInfoId, "
						+ "category.name AS categoryName, product.description, product.content, product.event, opening_hours, "
						+ "place_name, place_lot, place_street, tel, homepage, email, product.create_date, product.modify_date "
						+ "FROM category INNER JOIN product ON category.id = product.category_id " 
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE display_info.id = ?",
						new RowMapper<DisplayInfoDto>(){
							@Override
							public DisplayInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
								DisplayInfoDto displayInfoDto = new DisplayInfoDto();
								displayInfoDto.setCategoryId(rs.getInt("categoryId"));
								displayInfoDto.setCategoryName(rs.getString("categoryName"));
								displayInfoDto.setCreateDate(rs.getString("create_date"));
								displayInfoDto.setDisplayInfoId(rs.getInt("displayInfoId"));
								displayInfoDto.setEmail(rs.getString("email"));
								displayInfoDto.setHomepage(rs.getString("homepage"));
								displayInfoDto.setModifyDate(rs.getString("modify_date"));
								displayInfoDto.setOpeningHours(rs.getString("opening_hours"));
								displayInfoDto.setPlaceLot(rs.getString("place_lot"));
								displayInfoDto.setPlaceName(rs.getString("place_name"));
								displayInfoDto.setPlaceStreet(rs.getString("place_street"));
								displayInfoDto.setProductContent(rs.getString("content"));
								displayInfoDto.setProductDescription(rs.getString("description"));
								displayInfoDto.setProductEvent(rs.getString("event"));
								displayInfoDto.setProductId(rs.getInt("productId"));
								displayInfoDto.setTelephone(rs.getString("tel"));
								
								return displayInfoDto;
							}
						}, displayInfoId);

		return displayInfoList.isEmpty() ? null : displayInfoList.get(0);
	}
	
	public List<ProductImageDto> getProductImage(int displayInfoId) {
		List<ProductImageDto> productImageList = jdbcTemplate
				.query("SELECT product.id AS productId, product_image.id AS productImageId, product_image.type, "
						+ "file_info.id AS fileInfoId, file_info.save_file_name, file_info.content_type, file_info.delete_flag, "
						+ "file_info.create_date, file_info.modify_date, file_info.file_name "
						+ "FROM product INNER JOIN product_image ON product.id = product_image.product_id "
						+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE product_image.type IN ('ma','et') AND display_info.id = ?"
						,
						new RowMapper<ProductImageDto>(){
							@Override
							public ProductImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
								ProductImageDto productImageDto = new ProductImageDto();

								productImageDto.setCreateDate(rs.getString("create_date"));
								productImageDto.setDeleteFlag(rs.getString("delete_flag"));
								productImageDto.setFileInfoId(rs.getInt("fileInfoId"));
								productImageDto.setFileName(rs.getString("file_name"));
								productImageDto.setModifyDate(rs.getString("modify_date"));
								productImageDto.setProductId(rs.getInt("productId"));
								productImageDto.setProductImageId(rs.getInt("productImageId"));
								productImageDto.setSaveFileName(rs.getString("save_file_name"));
								productImageDto.setType(rs.getString("type"));
								return productImageDto;
							}
						}, displayInfoId);

		return productImageList.isEmpty() ? null : productImageList;
	}

	public DisplayInfoImageDto getDisplayInfoImage(int displayInfoId) {
		List<DisplayInfoImageDto> displayInfoImageList = jdbcTemplate
				.query("SELECT display_info_image.id AS displayInfoImageId, display_info.id AS displayInfoId, "
						+ "file_info.id AS fileId, file_info.file_name, file_info.save_file_name, file_info.content_type, "
						+ "file_info.delete_flag, file_info.create_date, file_info.modify_date "
						+ "FROM product INNER JOIN display_info ON product.id = display_info.product_id "
						+ "INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id "
						+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id "
						+ "WHERE display_info.id = ?"
						,
						new RowMapper<DisplayInfoImageDto>(){
							@Override
							public DisplayInfoImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
								DisplayInfoImageDto displayInfoImageDto = new DisplayInfoImageDto();

								displayInfoImageDto.setContentType(rs.getString("content_type"));
								displayInfoImageDto.setCreateDate(rs.getString("create_date"));
								displayInfoImageDto.setDeleteFlag(rs.getString("delete_flag"));
								displayInfoImageDto.setDisplayInfoId(rs.getInt("displayInfoId"));
								displayInfoImageDto.setDisplayInfoImageId(rs.getInt("displayInfoImageId"));
								displayInfoImageDto.setFilaName(rs.getString("file_name"));
								displayInfoImageDto.setFileId(rs.getInt("fileId"));
								displayInfoImageDto.setModifyDate(rs.getString("modify_date"));
								displayInfoImageDto.setSaveFileName(rs.getString("save_file_name"));
								return displayInfoImageDto;

							}
						}, displayInfoId);

		return displayInfoImageList.isEmpty() ? null : displayInfoImageList.get(0);
	}
	
	
	public List<ProductPriceDto> getProductPrice(int displayInfoId) {
		List<ProductPriceDto> productPriceList = jdbcTemplate
				.query("SELECT product_price.id AS productPriceId, product.id AS productId, product_price.price_type_name, "
						+ "product_price.price, product_price.discount_rate, product_price.create_date, product_price.modify_date "
						+ "FROM product INNER JOIN product_price ON product.id = product_price.product_id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE display_info.id = ? ORDER BY productPriceId desc"
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
	
	public List<Integer> getAverageScore(int displayInfoId) {
		List<Integer> averageScoreList = jdbcTemplate
				.query("SELECT reservation_user_comment.score "
						+ "FROM product INNER JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE display_info.id = ?"
						,
						new RowMapper<Integer>(){
							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt("score");
							}
						}, displayInfoId);

		return averageScoreList.isEmpty() ? null : averageScoreList;
	}

	public List<String> getComment(int displayInfoId) {
		List<String> commentList = jdbcTemplate
				.query("SELECT reservation_user_comment.comment "
						+ "FROM product INNER JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE display_info.id = ?"
						,
						new RowMapper<String>(){
							@Override
							public String mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getString("score");
							}
						}, displayInfoId);

		return commentList.isEmpty() ? null : commentList;
	}

}
