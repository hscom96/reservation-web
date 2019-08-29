package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.comment.CommentDto;

@Repository
public class ReservationCommentDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationCommentDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Double getAverageScore(int displayInfoId) {
		List<Double> averageScoreList = jdbcTemplate.query("SELECT AVG(reservation_user_comment.score) as averageScore " + 
				"FROM product INNER JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id " + 
				"INNER JOIN display_info ON product.id = display_info.product_id WHERE display_info.id = ?",
				new RowMapper<Double>() {
					@Override
					public Double mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getDouble("averageScore");
					}
				}, displayInfoId);

		return averageScoreList.isEmpty() ? null : averageScoreList.get(0);
	}

	public List<CommentDto> getComment(int displayInfoId) {
		List<CommentDto> commentList = jdbcTemplate.query(
				"select reservation_user_comment.id as commentId, product.id as productId, reservation_info.id as reservationInfoId, score, comment, reservation_name, reservation_tel, reservation_email, reservation_date, reservation_user_comment.create_date, reservation_user_comment.modify_date " + 
				"from product " + 
				"INNER JOIN reservation_info ON product.id = reservation_info.product_id " + 
				"INNER JOIN reservation_user_comment ON reservation_user_comment.reservation_info_id = reservation_info.id " + 
				"INNER JOIN display_info ON display_info.product_id = product.id " +
				"WHERE display_info.id = ? " + 
				"ORDER BY reservation_user_comment.id desc",
				new RowMapper<CommentDto>() {
					@Override
					public CommentDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						CommentDto commentDto = new CommentDto();
						commentDto.setCommentId(rs.getInt("commentId"));
						commentDto.setProductId(rs.getInt("productId"));
						commentDto.setReservationInfoId(rs.getInt("reservationInfoId"));
						commentDto.setScore(rs.getDouble("score"));
						commentDto.setComment(rs.getString("comment"));
						commentDto.setReservationName(rs.getString("reservation_name"));
						commentDto.setReservationTelephone(rs.getString("reservation_tel"));
						commentDto.setReservationEmail(rs.getString("reservation_email"));
						commentDto.setReservationDate(rs.getString("reservation_date"));
						commentDto.setCreateDate(rs.getString("create_date"));
						commentDto.setModifyDate(rs.getString("modify_date"));
						return commentDto;
					}
				}, displayInfoId);

		return commentList.isEmpty() ? new ArrayList() : commentList;
	}

}
