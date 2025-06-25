package com.company.ecommerce.repository;

import com.company.ecommerce.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, Users> {


}
