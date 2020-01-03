package or.connect.reservationweb.dto.productInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageDto {
	private int productId;
	private int productImageId;
	private String type;
	private int fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private String deleteFlag;
	private String createDate;
	private String modifyDate;
}