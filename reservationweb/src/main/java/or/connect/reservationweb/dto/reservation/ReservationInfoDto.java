package or.connect.reservationweb.dto.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfoDto {
	public int reservationInfoId;
	public int productId;
	public int displayInfoId;
	public String reservationName;
	public String reservationTelephone;
	public String reservationEmail;
	public Boolean cancelYn;
	public String reservationDate;
	public String createDate;
	public String modifyDate;

}