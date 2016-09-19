package com.starry.triones.oauth;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import com.starry.triones.domain.Users;
import com.starry.triones.repository.UsersRepository;

/**
 * An extension of the {@link UserDetailsService} which provides the ability to create new
 * users and update existing ones.
 */
@Component
public class TrionesUserDetailsManager implements UserDetailsManager {

	@Autowired
	private UsersRepository usersRepository;

	public TrionesUserDetailsManager(UsersRepository usersRepository) {
		super();
		this.usersRepository = usersRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users users = usersRepository.findByUsername(username);

		if (users == null) {
			throw new UsernameNotFoundException(username);
		}

		return new TrionesUserDetails(users);
	}

	@Override
	public void createUser(UserDetails userDetails) {
		TrionesUserDetails trionesUserDetails = (TrionesUserDetails) userDetails;
		usersRepository.save(trionesUserDetails.getUsers());
	}

	@Override
	public void updateUser(UserDetails userDetails) {
		TrionesUserDetails trionesUserDetails = (TrionesUserDetails) userDetails;
		usersRepository.save(trionesUserDetails.getUsers());
	}

	@Override
	public void deleteUser(String username) {
		usersRepository.delete(username);
	}

	@Override
	public void changePassword(String username, String newPassword) {
		Users user = usersRepository.findByUsername(username);
		user.setPassword(newPassword);
		usersRepository.save(user);
	}

	@Override
	public boolean userExists(String username) {
		long count = usersRepository.count(new Specification<Users>(){
			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("username").as(String.class), username);
			}}
		);

		if (count > 0) {
			return true;
		}
		return false;
	}

	public UsersRepository getUsersRepository() {
		return usersRepository;
	}

	public void setUsersRepository(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}


}
