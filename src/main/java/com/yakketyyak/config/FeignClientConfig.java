package com.yakketyyak.config;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
public class FeignClientConfig {

	/**
	 * Feign client configuration to ignore server ssl encrpytion - do not it use in
	 * production
	 *
	 * @return the client
	 */
	@Bean
	public Client feignClient() {
		Client trustSSLSockets = new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
		return trustSSLSockets;
	}

	/**
	 * Gets the SSL socket factory.
	 *
	 * @return the SSL socket factory
	 */
	private SSLSocketFactory getSSLSocketFactory() {
		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

				@Override
				public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					return true;
				}
			};

			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
			return sslContext.getSocketFactory();
		} catch (Exception exception) {
		}
		return null;
	}

}
