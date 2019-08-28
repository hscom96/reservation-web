package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.productInfo.DisplayInfoImageDto;
import or.connect.reservationweb.dto.productInfo.ProductImageDto;

@Repository
public class ProductImageDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ProductImageDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ProductImageDto> getProductImage(int displayInfoId) {
		List<ProductImageDto> productImageList = jdbcTemplate.query(
				"SELECT product.id AS productId, product_image.id AS productImageId, product_image.type, "
						+ "file_info.id AS fileInfoId, file_info.save_file_name, file_info.content_type, file_info.delete_flag, "
						+ "file_info.create_date, file_info.modify_date, file_info.file_name "
						+ "FROM product INNER JOIN product_image ON product.id = product_image.product_id "
						+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
						+ "INNER JOIN display_info ON product.id = display_info.product_id "
						+ "WHERE product_image.type IN ('ma','et') AND display_info.id = ?",
				new RowMapper<ProductImageDto>() {
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
						+ "WHERE display_info.id = ?", new RowMapper<DisplayInfoImageDto>() {
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
}
