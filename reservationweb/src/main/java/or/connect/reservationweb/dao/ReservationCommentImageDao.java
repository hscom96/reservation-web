package or.connect.reservationweb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import or.connect.reservationweb.dto.comment.CommentImageDto;

@Repository
public class ReservationCommentImageDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public ReservationCommentImageDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<CommentImageDto> getCommentImage(int commentId) {
		List<CommentImageDto> commentImageList = jdbcTemplate.query("SELECT reservation_user_comment_image.id AS imageId, reservation_info.id AS reservationInfoId, reservation_user_comment.id AS reservationUserCommentId, "
		+ "file_info.id AS fileId, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date "
		+ "FROM reservation_user_comment_image " 
		+ "INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment_image.reservation_info_id "
		+ "INNER JOIN reservation_user_comment ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id "
		+ "INNER JOIN file_info ON file_info.id = reservation_user_comment_image.file_id "
		+ "WHERE reservation_user_comment.id = ? ", 
		new RowMapper<CommentImageDto>() {
			@Override
			public CommentImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommentImageDto commentImageDto = new CommentImageDto();
				commentImageDto.setContentType(rs.getString("content_type"));
				commentImageDto.setCreateDate(rs.getString("create_date"));
				commentImageDto.setDeleteFlag(rs.getBoolean("delete_flag"));
				commentImageDto.setFileId(rs.getInt("fileId"));
				commentImageDto.setFileName(rs.getString("file_name"));
				commentImageDto.setImageId(rs.getInt("imageId"));
				commentImageDto.setModifyDate(rs.getString("modify_date"));
				commentImageDto.setReservationInfoId(rs.getInt("reservationInfoId"));
				commentImageDto.setReservationUserCommentId(rs.getInt("reservationUserCommentId"));
				commentImageDto.setSaveFileName(rs.getString("save_file_name"));
				
				return commentImageDto;
		}
			},commentId);
		
		return commentImageList.isEmpty()? null : commentImageList;
	}

}
