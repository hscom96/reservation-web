package dto.promotion;

public class PromotionDto {
	private int id;
	private int productId;
	private String productImageUrl;
	
	public PromotionDto(int id, int productId, String productImageUrl) {
		super();
		this.id = id;
		this.productId = productId;
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
