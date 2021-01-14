package com.yakketyyak.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Instantiates a new service url config.
 */
@Data
@ConfigurationProperties(prefix = "feign.countries")
@Component
public class ServiceUrlConfig {

	/** The url. */
	private String url;

}
