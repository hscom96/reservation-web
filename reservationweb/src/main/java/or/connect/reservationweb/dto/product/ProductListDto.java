package or.connect.reservationweb.dto.product;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListDto {
	private int totalCount;
	private List<ProductDto> items;

	public ProductListDto() {
		items = new ArrayList();
	}

	public void setItems(List<ProductDto> items) {
		if (items == null) 
			this.items = null;
		else 
			this.items.addAll(items);
		
	}
}
