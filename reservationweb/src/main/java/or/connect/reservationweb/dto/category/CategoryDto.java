package or.connect.reservationweb.dto.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
	private int id;
	private String name;
	private int count;

	public CategoryDto(int id, String name, int count) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
	}
}
