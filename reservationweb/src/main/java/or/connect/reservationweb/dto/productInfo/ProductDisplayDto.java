package or.connect.reservationweb.dto.productInfo;

import java.util.ArrayList;
import java.util.List;

public class ProductDisplayDto {
	private DisplayInfoDto displayInfo;
	private List<ProductImageDto> productImages;
	private List<String> comments;
	private List<Integer> averageScore;
	private List<ProductPriceDto> productPrices;
	private DisplayInfoImageDto displayInfoImage;
	
	public ProductDisplayDto() {
		this.productImages = new ArrayList();
		this.comments = new ArrayList();
		this.averageScore = new ArrayList();
		this.productPrices = new ArrayList();
	}
	
	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}
	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}
	public List<ProductImageDto> getProductImages() {
		return productImages;
	}
	public void setProductImages(List<ProductImageDto> productImages) {
		if (productImages == null) {
			this.productImages = null;
		} else {
			this.productImages.addAll(productImages);
		}
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		if (comments == null) {
			this.comments = null;
		} else {
			this.comments.addAll(comments);
		}
	}
	public List<Integer> getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(List<Integer> averageScore) {
		if (averageScore == null) {
			this.averageScore = null;
		} else {
			this.averageScore.addAll(averageScore);
		}
	}
	public List<ProductPriceDto> getProductPrices() {
		return productPrices;
	}
	public void setProductPrices(List<ProductPriceDto> productPrices) {
		if (productPrices == null) {
			this.productPrices = null;
		} else {
			this.productPrices.addAll(productPrices);
		}
	}
	public DisplayInfoImageDto getDisplayInfoImage() {
		return displayInfoImage;
	}
	public void setDisplayInfoImage(DisplayInfoImageDto displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

}
