package or.connect.reservationweb.dto.promotion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PromotionListDto {
	private List<PromotionDto> items;

	public PromotionListDto() {
		items = new ArrayList();
	}

	public void setItems(List<PromotionDto> items) {
		this.items.addAll(items);
	}

	public List<PromotionDto> getItems() {
		return items;
	}
}
