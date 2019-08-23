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
public class ReservationCommentDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationCommentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
								return rs.getString("comment");
							}
						}, displayInfoId);

		return commentList.isEmpty() ? null : commentList;
	}

}
