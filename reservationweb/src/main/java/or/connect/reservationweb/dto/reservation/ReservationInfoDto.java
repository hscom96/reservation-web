package or.connect.reservationweb.dto.reservation;

public class ReservationInfoDto {
	public int reservationInfoId;
	public int productId;
	public int displayInfoId;
	public String reservationName;
	public String reservationTelephone;
	public Boolean cancelYn;
	public String reservationDate;
	public String createDate;
	public String modifyDate;
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTelephone() {
		return reservationTelephone;
	}
	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}
	public Boolean getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(Boolean cancelYn) {
		this.cancelYn = cancelYn;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
/*
SELECT reservation_info.id AS reservationInfoId, product_id AS productId, display_info_id AS displayInfoId,
reservation_name, reservation_tel, reservation_email, cancel_flag, reservation_date, create_date, modify_date
FROM reservation_info 
WHERE reservation_email = "carami@connect.co.kr" */