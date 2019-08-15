package dto.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductListDto {
	private int totalCount;
	private List<ProductDto> productList;
	
	public ProductListDto() {
		productList = new ArrayList();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList.addAll(productList);
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return productList.isEmpty();
	}
	
}
