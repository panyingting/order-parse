package com.jiao.order.parse.repository;

import com.jiao.order.parse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
