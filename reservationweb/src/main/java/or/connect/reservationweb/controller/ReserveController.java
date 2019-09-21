package or.connect.reservationweb.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reserve")
public class ReserveController {
	@GetMapping
	public String showView(Model model) {
		String time = getRandomDateFromNow();
		model.addAttribute("dateTime", time);
		return "reserve";
	}
	
	public String getRandomDateFromNow() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		LocalDateTime localDateTime = LocalDateTime.now();
		Random random = new Random();
		int randomDay = random.nextInt(6);
		LocalDateTime futureLocalDateTime = localDateTime.plusDays(randomDay);
		String time = futureLocalDateTime.format(dateTimeFormatter);
		
		return time;
	}
}
