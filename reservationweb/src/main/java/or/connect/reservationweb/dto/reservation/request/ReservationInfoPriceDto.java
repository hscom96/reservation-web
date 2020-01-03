package or.connect.reservationweb.dto.reservation.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import or.connect.reservationweb.dto.reservation.ReservationInfoDto;

@Getter
@Setter
public class ReservationInfoPriceDto {
	private int reservationInfoId;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationYearMonthDay;
	private Boolean cancelYn;
	private String createDate;
	private String modifyDate;
	private List<ReservationPriceDto> prices;
	
	public ReservationInfoPriceDto() {
		prices = new ArrayList<>();
		cancelYn = false;
		createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		modifyDate = createDate;
		prices = null;
	}

	public void setReservationInfo(ReservationInfoDto reservationInfo) {
		reservationInfoId = reservationInfo.getReservationInfoId();
		productId = reservationInfo.getProductId();
		displayInfoId = reservationInfo.getDisplayInfoId();
		reservationName = reservationInfo.getReservationName();
		reservationTelephone = reservationInfo.getReservationTelephone();
		cancelYn = reservationInfo.getCancelYn();
		reservationYearMonthDay = reservationInfo.getReservationDate();
		createDate = reservationInfo.getCreateDate();
		modifyDate = reservationInfo.getModifyDate();
		reservationEmail = reservationInfo.getReservationEmail();
	}
	
	public void setReservationInfoIdAll(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
		for (ReservationPriceDto price : this.prices) {
			price.setReservationInfoId(reservationInfoId);;
		}
		
	}
}
