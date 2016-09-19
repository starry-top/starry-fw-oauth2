package com.starry.triones.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.starry.triones.domain.Users;

public interface UsersRepository extends CrudRepository<Users, String>, JpaSpecificationExecutor<Users> {

	Users findByUsername(String username);

}
