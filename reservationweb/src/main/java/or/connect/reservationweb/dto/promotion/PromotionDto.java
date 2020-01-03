package or.connect.reservationweb.dto.promotion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionDto {
	private int id;
	private int productId;
	private String productImageUrl;
	private String description;
	private String placeName;

	public PromotionDto(int id, int productId, String productImageUrl, String description, String placeName) {
		this.id = id;
		this.productId = productId;
		this.productImageUrl = productImageUrl;
		this.description = description;
		this.placeName = placeName;
	}

}
