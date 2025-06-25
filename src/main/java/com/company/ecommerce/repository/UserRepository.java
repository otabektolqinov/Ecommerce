package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByIdAndDeletedAtIsNull(Long id);

}
