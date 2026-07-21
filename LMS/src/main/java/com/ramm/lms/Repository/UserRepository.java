package com.ramm.lms.Repository;

import com.ramm.lms.Entity.Role;
import com.ramm.lms.Entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<Users,String> {
    Optional<Users> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Users> findByRole(Role role);
}

