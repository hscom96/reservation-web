package or.connect.reservationweb.dto.reservation;

import lombok.Getter;
import lombok.Setter;
import or.connect.reservationweb.dto.productInfo.DisplayInfoDto;

@Getter
@Setter
public class ReservationInfoSetItem {
	public int reservationInfoId;
	public int productId;
	public int displayInfoId;
	public String reservationName;
	public String reservationTelephone;
	public Boolean cancelYn;
	public String reservationDate;
	public String createDate;
	public String modifyDate;
	public String reservationEmail;
	
	public DisplayInfoDto displayInfo;
	
	int totalPrice;
	
	public void setReservationInfo(ReservationInfoDto reservationInfo) {
		reservationInfoId = reservationInfo.getReservationInfoId();
		productId = reservationInfo.getProductId();
		displayInfoId = reservationInfo.getDisplayInfoId();
		reservationName = reservationInfo.getReservationName();
		reservationTelephone = reservationInfo.getReservationTelephone();
		cancelYn = reservationInfo.getCancelYn();
		reservationDate = reservationInfo.getReservationDate();
		createDate = reservationInfo.getCreateDate();
		modifyDate = reservationInfo.getModifyDate();
		reservationEmail = reservationInfo.getReservationEmail();
	}
}
