package com.virtusa.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.ecommerce.model.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, String>{

}
