package com.jiao.order.parse.repository;

import com.jiao.order.parse.entity.MessageRegular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
MessageRegularRepository extends JpaRepository<MessageRegular, Integer> {

    List<MessageRegular> findByTypeId(int typeId);
}
