package or.connect.reservationweb.dto.promotion;

public class PromotionDto {
	private int id;
	private int productId;
	private String productImageUrl;
	private String description;

	public PromotionDto(int id, int productId, String productImageUrl, String description) {
		this.id = id;
		this.productId = productId;
		this.productImageUrl = productImageUrl;
		this.description = description;
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
