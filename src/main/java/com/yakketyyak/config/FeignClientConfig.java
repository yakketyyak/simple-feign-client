package com.yakketyyak.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;

@Configuration
@Slf4j
public class FeignClientConfig {

	/**
	 * Feign client configuration to ignore server ssl encrpytion - do not it use in
	 * production
	 *
	 * @return the client
	 */
	@Bean
	public Client feignClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		return new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier());
	}

	/**
	 * Gets the SSL socket factory.
	 *
	 * @return the SSL socket factory
	 */
	private SSLSocketFactory getSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (java.security.cert.X509Certificate[] chain, String authType) -> true;
		return SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build().getSocketFactory();
	}

}
