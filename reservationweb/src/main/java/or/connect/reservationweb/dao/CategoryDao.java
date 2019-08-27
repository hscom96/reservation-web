package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.category.CategoryDto;

@Repository
public class CategoryDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CategoryDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<CategoryDto> getCategoryList() {
		List<CategoryDto> categoryList = jdbcTemplate.query("SELECT category.id, category.name ,COUNT(*) AS count "
				+ "FROM category " + "INNER JOIN product ON category.id = product.category_id "
				+ "INNER JOIN display_info ON product.id = display_info.product_id "
				+ "group by category.id, category.name;", new RowMapper<CategoryDto>() {
					@Override
					public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						CategoryDto categoryDto = new CategoryDto(rs.getInt("id"), rs.getString("name"),
								rs.getInt("count"));
						return categoryDto;
					}
				});
		return categoryList.isEmpty() ? null : categoryList;
	}
}
