package or.connect.reservationweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import or.connect.reservationweb.dao.ProductDisplayDao;
import or.connect.reservationweb.dao.ReservationDao;
import or.connect.reservationweb.dao.ReservationPriceDao;
import or.connect.reservationweb.dto.productInfo.DisplayInfoDto;
import or.connect.reservationweb.dto.reservation.ReservationInfoDto;
import or.connect.reservationweb.dto.reservation.ReservationInfoSetDto;
import or.connect.reservationweb.dto.reservation.ReservationItem;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private ReservationPriceDao reservationPriceDao;
	@Autowired
	private ProductDisplayDao productDisplayDao;
	
	public ReservationInfoSetDto getReservationInfo(String reservationEmail) {
		ReservationInfoSetDto reservationInfoSet = new ReservationInfoSetDto();
		
		List<ReservationInfoDto> reservationInfoList = reservationDao.getResrvationInfoList(reservationEmail);
		
		for(ReservationInfoDto reservationInfo : reservationInfoList) {
			ReservationItem reservationItem = new ReservationItem();
			int totalPrice = reservationPriceDao.getTotalPrice(reservationInfo.getReservationInfoId());
			DisplayInfoDto displayInfo = productDisplayDao.getDisplayInfo(reservationInfo.getDisplayInfoId());
			
			reservationItem.setReservationInfo(reservationInfo);
			reservationItem.setDisplayInfo(displayInfo);
			reservationItem.setTotalPrice(totalPrice);
			
			reservationInfoSet.addReservationItem(reservationItem);
			reservationInfoSet.addSize(1);
		}
		
		
		return reservationInfoSet;
	}
}
