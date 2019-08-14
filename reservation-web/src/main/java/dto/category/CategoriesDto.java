package dto.category;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoriesDto {
	List<CategoryDto> items;
	
	
	
	public CategoriesDto() {
		items = new ArrayList();
	}

	public List<CategoryDto> getItems() {
		return items;
	}

	public void setItems(List<CategoryDto> items) {
		this.items.addAll(items);
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return items.isEmpty();
	}
}
