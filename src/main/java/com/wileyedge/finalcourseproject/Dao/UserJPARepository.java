package com.wileyedge.finalcourseproject.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wileyedge.finalcourseproject.model.User;

@Repository(value= "jparepos")
public interface UserJPARepository extends JpaRepository<User, Integer>, IDao {
}
