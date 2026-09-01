package io.mosip.authentication.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Keeps the health URLs of the former internal and OTP microservices valid
 * when they run from the consolidated IDA application.
 */
@Controller
public class LegacyActuatorPathController {

	@GetMapping({
			"/internal/actuator/health",
			"/otp/actuator/health"
	})
	public String health() {
		return "forward:/actuator/health";
	}
}
