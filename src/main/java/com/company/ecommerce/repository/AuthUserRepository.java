package com.company.ecommerce.repository;

import com.company.ecommerce.domain.AuthUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Long> {
    Optional<AuthUser> findByPhoneNumber(String phoneNumber);
}
