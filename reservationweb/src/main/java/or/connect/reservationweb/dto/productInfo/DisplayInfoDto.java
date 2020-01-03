package or.connect.reservationweb.dto.productInfo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayInfoDto {
	private int productId;
	private int categoryId;
	private int displayInfoId;
	private String categoryName;
	private String productDescription;
	private String productContent;
	private String productEvent;
	private String openingHours;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String telephone;
	private String homepage;
	private String email;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
}
