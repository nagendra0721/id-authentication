package io.mosip.authentication.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerUiAssetConfig implements WebMvcConfigurer {

	private static final String SWAGGER_UI_WEBJAR =
			"classpath:/META-INF/resources/webjars/swagger-ui/5.13.0/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(
						"/internal/swagger-ui/**",
						"/otp/swagger-ui/**")
				.addResourceLocations(SWAGGER_UI_WEBJAR);
	}
}
