package io.mosip.authentication.service.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Preserves former internal and OTP service paths after consolidation.
 */
@Controller
public class LegacyServiceRouteController {

	@PostMapping({
			"/internal/callback/masterdata/templates",
			"/internal/callback/masterdata/titles",
			"/internal/callback/partnermanagement/ca_certificate",
			"/otp/callback/masterdata/templates",
			"/otp/callback/masterdata/titles",
			"/otp/callback/partnermanagement/ca_certificate"
	})
	public String callback(HttpServletRequest request) {
		return forwardWithoutServicePrefix(request);
	}

	private String forwardWithoutServicePrefix(HttpServletRequest request) {
		return "forward:" + pathWithoutServicePrefix(request);
	}

	private String pathWithoutServicePrefix(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		if (path.startsWith("/internal")) {
			return path.substring("/internal".length());
		}
		return path.substring("/otp".length());
	}
}
