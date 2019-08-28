package or.connect.reservationweb.dto.product;

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

	public void setItems(List<ProductDto> items) {
		if (items == null) 
			this.items = null;
		else 
			this.items.addAll(items);
		
	}
}
