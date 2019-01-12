package com.wl.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Site.
 * <p>
 * Properties are configured in the application.yml file. See
 * {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private String staticResourcePath;

	public String getStaticResourcePath() {
		return staticResourcePath;
	}

	public void setStaticResourcePath(String staticResourcePath) {
		this.staticResourcePath = staticResourcePath;
	}

	

}
