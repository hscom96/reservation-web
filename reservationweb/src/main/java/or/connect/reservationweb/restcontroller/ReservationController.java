package or.connect.reservationweb.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.reservation.ReservationInfoSetDto;
import or.connect.reservationweb.dto.reservation.request.ReservationInfoPriceDto;
import or.connect.reservationweb.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	public ReservationInfoSetDto getReservationInfoSet(@RequestParam(value="reservationEmail", required=true) String reservationEmail) {
		return reservationService.getReservationInfoSet(reservationEmail);
	}
	
	@PostMapping
	public @ResponseBody ReservationInfoPriceDto registReservationInfo(@RequestBody ReservationInfoPriceDto reservationInfoPriceDto) {
		return reservationService.registerReservation(reservationInfoPriceDto);
	}
	
	@PutMapping("/{reservationId}")
	public ReservationInfoPriceDto deleteReservationInfo(@PathVariable int reservationId) {
		return reservationService.setCancelReservation(reservationId);
	}
}
