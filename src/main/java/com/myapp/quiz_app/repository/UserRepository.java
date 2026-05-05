package com.myapp.quiz_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.quiz_app.model.User;//import the class that has the table

@Repository
public interface UserRepository extends JpaRepository<User, Long>//<table name,primary key datatype>
{

    Optional<User> findByUsername(String username);//prevent null pointer exceptions
}                                                  //Dont know if user exists or not