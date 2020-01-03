package or.connect.reservationweb.dto.comment;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private int commentId;
	private int productId;
	private int reservationInfoId;
	private double score;
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

	public void setCommentImages(List<CommentImageDto> commentImages) {
		if(commentImages == null) 
			this.commentImages=null;
		else 
			this.commentImages.addAll(commentImages);
	}
}
