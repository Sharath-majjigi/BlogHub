package com.example.sharath.BlogHub.Repository;

import com.example.sharath.BlogHub.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDAO extends JpaRepository<User,Long> {

    User findByEmail(String email);

}
