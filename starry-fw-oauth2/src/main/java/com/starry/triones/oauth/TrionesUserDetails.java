package com.starry.triones.oauth;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import com.starry.triones.domain.Users;

public class TrionesUserDetails extends User {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	protected static final String ROLE_PREFIX = "ROLE_";
	protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");

	private String displayName;
	private String realName;
	private String phone;
	private String email;
	private String roles;
	private Users users;



	public TrionesUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public TrionesUserDetails(Users users) {
		this(users.getUsername(), users.getPassword(),
				users.isDisabled(), users.isAccountExpired(), users.isCredentialsExpired(), users.isAccountLocked(),
				initialAuthorities(users.getRoles()));
		this.displayName = users.getDisplayName();
		this.realName = users.getRealName();
		this.email = users.getEmail();
		this.phone = users.getPhone();
		this.roles = users.getRoles();
		this.users = users;
	}

	private static Set<GrantedAuthority> initialAuthorities(String roles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(DEFAULT_USER_ROLE);
		if (roles != null) {
			String[] rolesArray = StringUtils.split(roles, ",");
			if (rolesArray != null) {
				for (String role : rolesArray) {
					grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
				}
			} else {
				grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + roles));
			}

		}
		return grantedAuthorities;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "TrionesUserDetails [" + super.toString() + "; displayName: " + displayName + "; realName: " + realName + "; phone: " + phone
				+ "; email: " + email + "; roles: " + roles + "]";
	}
}
