package dto.promotion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PromotionsDto {
	private List<PromotionDto> items;

	public PromotionsDto() {
		items = new ArrayList();
	}
	
	public void setItems(List<PromotionDto> items) {
		this.items.addAll(items);
	}

	public List<PromotionDto> getItems() {
		return items;
	}
	
	@JsonIgnore
	public boolean isEmpty() {
		return items.isEmpty();
	}
}
