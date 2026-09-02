package io.mosip.authentication.service.controller;

import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * Preserves former internal and OTP service paths after consolidation.
 */
@Controller
public class LegacyServiceRouteController {

	private static final Set<String> INTERNAL_KERNEL_ENDPOINTS = Set.of(
			"generateMasterKey", "getCertificate", "generateCSR",
			"uploadCertificate", "uploadOtherDomainCertificate",
			"generateSymmetricKey", "revokeKey", "getAllCertificates",
			"generateECSignKey", "getCertificateChain",
			"sign", "validate", "pdf", "jwtSign", "jwtVerify", "jwsSign",
			"signV2", "signRawData",
			"uploadCACertificate", "uploadPartnerCertificate",
			"getPartnerCertificate", "verifyCertificateTrust",
			"getPartnerSignedCertificate", "getCaCertificates",
			"getCACertificateTrustPath",
			"encrypt", "decrypt", "encryptWithPin", "decryptWithPin",
			"jwtEncrypt", "jwtDecrypt", "generateArgon2Hash");

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

	@RequestMapping("/internal/**")
	public String internalKernelEndpoint(HttpServletRequest request) {
		String rootPath = pathWithoutServicePrefix(request);
		String[] segments = rootPath.split("/", 3);

		if (segments.length < 2 || !INTERNAL_KERNEL_ENDPOINTS.contains(segments[1])) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return "forward:" + rootPath;
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
