package io.mosip.authentication.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Restores the URL prefix of the former internal IDA microservice after its
 * controllers are hosted by the consolidated application.
 */
@Configuration
public class ConsolidatedIdaRouteConfig implements WebMvcConfigurer {

	private static final String INTERNAL_CONTROLLER_PACKAGE =
			"io.mosip.authentication.service.internal.controller";

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.addPathPrefix("/internal",
				HandlerTypePredicate.forBasePackage(INTERNAL_CONTROLLER_PACKAGE));
	}
}
