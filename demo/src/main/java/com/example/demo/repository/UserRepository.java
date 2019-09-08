package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	List<User> findByFirstname(String firstname);

}