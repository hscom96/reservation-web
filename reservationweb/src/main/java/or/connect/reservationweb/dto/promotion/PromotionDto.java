package or.connect.reservationweb.dto.promotion;

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
	
	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}


	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

}
