package or.connect.reservationweb.dto.category;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryListDto {
	List<CategoryDto> items;

	public CategoryListDto() {
		items = new ArrayList();
	}

	public List<CategoryDto> getItems() {
		return items;
	}

	public void setItems(List<CategoryDto> items) {
		this.items.addAll(items);
	}
	
	
}
