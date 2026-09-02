package io.mosip.authentication.service.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Keeps the actuator URLs of the former internal and OTP microservices valid
 * when they run from the consolidated IDA application.
 */
@Controller
public class LegacyActuatorPathController {

	@RequestMapping({
			"/internal/actuator/**",
			"/otp/actuator/**"
	})
	public String actuator(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/internal")) {
			return "forward:" + path.substring("/internal".length());
		}
		return "forward:" + path.substring("/otp".length());
	}
}
