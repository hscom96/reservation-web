package or.connect.reservationweb.dto.reservation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationPriceDto {
	private int	reservationInfoPriceId;
	private int reservationInfoId;
	private int productPriceId;
	private int count;
}
