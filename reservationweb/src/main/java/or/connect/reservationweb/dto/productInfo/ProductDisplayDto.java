package or.connect.reservationweb.dto.productInfo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import or.connect.reservationweb.dto.comment.CommentDto;

@Getter
@Setter
public class ProductDisplayDto {
	private DisplayInfoDto displayInfo;
	private List<ProductImageDto> productImages;
	private List<CommentDto> comments;
	private double averageScore;
	private List<ProductPriceDto> productPrices;
	private DisplayInfoImageDto displayInfoImage;

	public ProductDisplayDto() {
		this.productImages = new ArrayList();
		this.comments = new ArrayList();
		this.productPrices = new ArrayList();
	}

	public void setProductImages(List<ProductImageDto> productImages) {
		if (productImages == null) {
			this.productImages = null;
		} else {
			this.productImages.addAll(productImages);
		}
	}

	public void setComments(List<CommentDto> comments) {
		if (comments == null) {
			this.comments = null;
		} else {
			this.comments.addAll(comments);
		}
	}

	public void setProductPrices(List<ProductPriceDto> productPrices) {
		if (productPrices == null) {
			this.productPrices = null;
		} else {
			this.productPrices.addAll(productPrices);
		}
	}


}
