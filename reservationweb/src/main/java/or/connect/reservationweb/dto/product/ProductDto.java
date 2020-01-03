package or.connect.reservationweb.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private int displayId;
	private int productId;
	private String productDescription;
	private String placeName;
	private String productContent;
	private String productImgUrl;

	public ProductDto(int displayId, int productId, String productDescription, String placeName, String productContent,
			String productImgUrl) {
		super();
		this.displayId = displayId;
		this.productId = productId;
		this.productDescription = productDescription;
		this.placeName = placeName;
		this.productContent = productContent;
		this.productImgUrl = productImgUrl;
	}
}
