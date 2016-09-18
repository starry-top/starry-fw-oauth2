package com.starry.triones.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 客户端实体
 */
@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
@Table(name="oauth_client_details")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class OAuthClientDetails implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public OAuthClientDetails() {
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String clientId;

	private String clientSecret;

	private String resourceIds;
	/**
	 * Available values: read,write
	 */
	private String scope;
	/**
	 * grant types include
	 * "authorization_code", "password", "assertion", and "refresh_token".
	 * Default value is "authorization_code,refresh_token".
	 */
	private String authorizedGrantTypes;
	/**
	 * The re-direct URI(s) established during registration (optional, comma separated).
	 */
	private String webServerRedirectUri;
	/**
	 * Authorities that are granted to the client (comma-separated). Distinct from the authorities
	 * granted to the user on behalf of whom the client is acting.
	 * <p/>
	 * For example: ROLE_USER
	 */
	private String authorities;
	/**
	 * The access token validity period in seconds (optional).
	 * If unspecified a global default will be applied by the token services.
	 */
	private Integer accessTokenValidity;
	/**
	 * The refresh token validity period in seconds (optional).
	 * If unspecified a global default will  be applied by the token services.
	 */
	private Integer refreshTokenValidity;
	// optional
	private String additionalInformation;

	/**
	 * Value is 'true' or 'false',  default 'false'
	 */
	@Column(name="autoapprove")
	private String autoApprove;


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}


}
