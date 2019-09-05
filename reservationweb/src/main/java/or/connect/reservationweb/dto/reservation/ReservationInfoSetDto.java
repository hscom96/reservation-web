package or.connect.reservationweb.dto.reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationInfoSetDto {
	public List<ReservationItem> reservations;
	public int size;
	
	public ReservationInfoSetDto() {
		this.reservations = new ArrayList();
		this.size = 0;
	}
	public void addReservationItem(ReservationItem reservationItem) {
			this.reservations.add(reservationItem);
	}
	public void addSize(int count) {
		size += count;
	}
	public List<ReservationItem> getReservations() {
		return reservations;
	}
	public void setReservations(List<ReservationItem> reservations) {
		if (reservations == null) 
			this.reservations = null;
		else 
			this.reservations.addAll(reservations);
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
