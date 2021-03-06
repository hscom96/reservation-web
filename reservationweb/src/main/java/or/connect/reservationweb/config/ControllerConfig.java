package or.connect.reservationweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "or.connect.reservationweb.controller", "or.connect.reservationweb.restcontroller" })
public class ControllerConfig {
}
