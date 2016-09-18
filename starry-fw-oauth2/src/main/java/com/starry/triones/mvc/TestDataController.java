package com.starry.triones.mvc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestDataController {

	private static final String TRIONES_RESOURCE_ID = "triones";

	@Autowired
	private DataSource dataSource;

	@RequestMapping(value="/test/insertClientData")
	@ResponseBody
	public String createClientTestData() throws Exception {
		JdbcClientDetailsServiceBuilder client = new JdbcClientDetailsServiceBuilder();
		client.dataSource(dataSource)
			.withClient("admin-trusted-client")
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
	            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
	            .scopes("read", "write", "trust")
	            .accessTokenValiditySeconds(60)
		    .and()
			.withClient("tonr")
	 			.resourceIds(TRIONES_RESOURCE_ID)
	 			.authorizedGrantTypes("authorization_code", "implicit")
	 			.authorities("ROLE_CLIENT")
	 			.scopes("read", "write")
	 			.secret("secret")
	 		.and()
	 		.withClient("tonr-with-redirect")
	 			.resourceIds(TRIONES_RESOURCE_ID)
	 			.authorizedGrantTypes("authorization_code", "implicit")
	 			.authorities("ROLE_CLIENT")
	 			.scopes("read", "write")
	 			.secret("secret")
	 			.redirectUris("http://localhost:8080/client/")
	 		.and()
			    .withClient("my-client-with-registered-redirect")
			        .resourceIds(TRIONES_RESOURCE_ID)
			        .authorizedGrantTypes("authorization_code", "client_credentials")
			        .authorities("ROLE_CLIENT")
			        .scopes("read", "trust")
			        .redirectUris("http://anywhere?key=value")
			    .and()
		        .withClient("my-trusted-client")
		            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
		            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
		            .scopes("read", "write", "trust")
		            .accessTokenValiditySeconds(60)
			    .and()
		        .withClient("my-trusted-client-with-secret")
		            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
		            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
		            .scopes("read", "write", "trust")
		            .secret("somesecret")
		        .and()
	         .withClient("my-less-trusted-client")
	            .authorizedGrantTypes("authorization_code", "implicit")
	            .authorities("ROLE_CLIENT")
	            .scopes("read", "write", "trust")
		        .and()
	        .withClient("my-less-trusted-autoapprove-client")
	            .authorizedGrantTypes("implicit")
	            .authorities("ROLE_CLIENT")
	            .scopes("read", "write", "trust")
	            .autoApprove(true);
		client.build();
		return "OK";
	}

}
