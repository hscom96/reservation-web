package or.connect.reservationweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import or.connect.reservationweb.dao.ProductDisplayDao;
import or.connect.reservationweb.dao.ReservationDao;
import or.connect.reservationweb.dao.ReservationPriceDao;
import or.connect.reservationweb.dto.productInfo.DisplayInfoDto;
import or.connect.reservationweb.dto.reservation.ReservationInfoDto;
import or.connect.reservationweb.dto.reservation.ReservationInfoSetDto;
import or.connect.reservationweb.dto.reservation.ReservationItem;
import or.connect.reservationweb.dto.reservation.request.ReservationPriceDto;
import or.connect.reservationweb.dto.reservation.request.ReservationRequest;

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
	
	@Transactional
	public ReservationRequest registerReservation(ReservationRequest reservationRequest) {
		int reservationInfoId = reservationDao.registerReservation(reservationRequest);
		int reservationInfoPriceId;
		List<ReservationPriceDto> changePrices = new ArrayList<>();
		
		for(ReservationPriceDto price : reservationRequest.getPrices()) {
			price.setReservationInfoId(reservationInfoId);
			reservationInfoPriceId = reservationPriceDao.registerPrice(price);
			price.setReservationInfoPriceId(reservationInfoPriceId);
			changePrices.add(price);
		}
		
		reservationRequest.setPrices(changePrices);
		reservationRequest.setReservationInfoId(reservationInfoId);
		
		return reservationRequest;
	}
}
