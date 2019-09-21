package or.connect.reservationweb.service;

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
import or.connect.reservationweb.dto.reservation.ReservationInfoSetItem;
import or.connect.reservationweb.dto.reservation.request.ReservationInfoPriceDto;
import or.connect.reservationweb.dto.reservation.request.ReservationPriceDto;

@Service
public class ReservationService {
	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private ReservationPriceDao reservationPriceDao;
	@Autowired
	private ProductDisplayDao productDisplayDao;

	public ReservationInfoSetDto getReservationInfoSet(String reservationEmail) {
		ReservationInfoSetDto reservationInfoSet = new ReservationInfoSetDto();

		List<ReservationInfoDto> reservationInfoList = reservationDao.getResrvationInfoList(reservationEmail);

		for (ReservationInfoDto reservationInfo : reservationInfoList) {
			ReservationInfoSetItem reservationItem = new ReservationInfoSetItem();
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
	public ReservationInfoPriceDto registerReservation(ReservationInfoPriceDto reservationDto) {
		ReservationInfoPriceDto updatedReservationInfo;
		int reservationInfoId = reservationDao.registerReservation(reservationDto);
		reservationDto.setReservationInfoIdAll(reservationInfoId);

		for (ReservationPriceDto price : reservationDto.getPrices()) {
			if (price.getCount() > 0) {
				reservationPriceDao.registerPrice(price);
			}
		}

		updatedReservationInfo = getReservationinfo(reservationInfoId);

		return updatedReservationInfo;
	}

	public ReservationInfoPriceDto setCancelReservation(int reservationId) {
		reservationDao.setCancelReservation(reservationId);
		ReservationInfoPriceDto updatedReservationInfo = getReservationinfo(reservationId);

		return updatedReservationInfo;
	}

	public ReservationInfoPriceDto getReservationinfo(int reservationId) {
		ReservationInfoPriceDto reservationInfoPriceDto = new ReservationInfoPriceDto();
		ReservationInfoDto reservationInfoDto = reservationDao.getResrvationInfo(reservationId);
		List<ReservationPriceDto> prices = reservationPriceDao.getPriceList(reservationId);

		reservationInfoPriceDto.setReservationInfo(reservationInfoDto);
		reservationInfoPriceDto.setPrices(prices);

		return reservationInfoPriceDto;
	}

	public boolean existReservation(String reservationEmail) {
		ReservationInfoSetDto reservationInfoSet = new ReservationInfoSetDto();
		List<ReservationInfoDto> reservationInfoList = reservationDao.getResrvationInfoList(reservationEmail);

		if (reservationInfoList == null) {
			return false;
		} else {
			return true;
		}
	}
}
