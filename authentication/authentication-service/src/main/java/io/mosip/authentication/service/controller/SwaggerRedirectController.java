package io.mosip.authentication.service.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Hidden
public class SwaggerRedirectController {

	@GetMapping(value = {
			"/swagger-ui",
			"/swagger-ui/",
			"/swagger-ui/index.html"
	}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String externalSwagger(HttpServletRequest request) {
		return swaggerUi(request.getContextPath() + "/v3/api-docs/external");
	}

	@GetMapping(value = {
			"/internal/swagger-ui",
			"/internal/swagger-ui/",
			"/internal/swagger-ui/index.html"
	}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String internalSwagger(HttpServletRequest request) {
		return swaggerUi(request.getContextPath() + "/v3/api-docs/internal");
	}

	@GetMapping(value = {
			"/otp/swagger-ui",
			"/otp/swagger-ui/",
			"/otp/swagger-ui/index.html"
	}, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String otpSwagger(HttpServletRequest request) {
		return swaggerUi(request.getContextPath() + "/v3/api-docs/otp");
	}

	private String swaggerUi(String openApiUrl) {
		return """
				<!doctype html>
				<html lang="en">
				<head>
				    <meta charset="utf-8">
				    <title>IDA API documentation</title>
				    <link rel="stylesheet" href="./swagger-ui.css">
				</head>
				<body>
				<div id="swagger-ui"></div>
				<script src="./swagger-ui-bundle.js"></script>
				<script src="./swagger-ui-standalone-preset.js"></script>
				<script>
				window.onload = function() {
				    window.ui = SwaggerUIBundle({
				        url: '%s',
				        dom_id: '#swagger-ui',
				        deepLinking: true,
				        presets: [SwaggerUIBundle.presets.apis, SwaggerUIStandalonePreset],
				        layout: 'StandaloneLayout'
				    });
				};
				</script>
				</body>
				</html>
				""".formatted(openApiUrl);
	}
}
