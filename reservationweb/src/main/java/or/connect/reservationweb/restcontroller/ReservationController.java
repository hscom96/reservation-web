package or.connect.reservationweb.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import or.connect.reservationweb.dto.reservation.ReservationInfoSetDto;
import or.connect.reservationweb.dto.reservation.request.ReservationRequest;
import or.connect.reservationweb.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	public ReservationInfoSetDto getReservationInfo(String reservationEmail) {
		return reservationService.getReservationInfo(reservationEmail);
	}
	
	@PostMapping
	public @ResponseBody ReservationRequest registReservationInfo(@RequestBody ReservationRequest reservationRequest) {
		return reservationService.registerReservation(reservationRequest);
	}
}
