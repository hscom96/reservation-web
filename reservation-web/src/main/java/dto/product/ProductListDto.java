package dto.product;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductListDto {
	private int totalCount;
	private List<ProductDto> items;
	
	public ProductListDto() {
		items = new ArrayList();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ProductDto> getitems() {
		return items;
	}

	public void setitems(List<ProductDto> items) {
		this.items.addAll(items);
	}
	}
