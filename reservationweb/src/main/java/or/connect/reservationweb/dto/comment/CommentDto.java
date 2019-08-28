package or.connect.reservationweb.dto.comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
	private int commentId;
	private int productId;
	private int reservationInfoId;
	private int score;
	private String comment;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationDate;
	private String createDate;
	private String modifyDate;
	private List<CommentImageDto> commentImages;

	public CommentDto() {
		this.commentImages = new ArrayList();
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<CommentImageDto> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImageDto> commentImages) {
		if(commentImages == null) 
			commentImages=null;
		else 
			this.commentImages.addAll(commentImages);
	}

	
}
