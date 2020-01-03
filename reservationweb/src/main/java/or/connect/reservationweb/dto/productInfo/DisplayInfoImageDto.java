package or.connect.reservationweb.dto.productInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayInfoImageDto {
	private int displayInfoImageId;
	private int displayInfoId;
	private int fileId;
	private String filaName;
	private String saveFileName;
	private String contentType;
	private String deleteFlag;
	private String createDate;
	private String modifyDate;


}
