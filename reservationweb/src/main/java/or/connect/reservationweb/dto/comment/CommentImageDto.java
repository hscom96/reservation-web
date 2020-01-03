package or.connect.reservationweb.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentImageDto {
	private int imageId;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private int fileId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private Boolean deleteFlag;
	private String createDate;
	private String modifyDate;
}
