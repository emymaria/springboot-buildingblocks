package com.stacksimplify.restservices.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;

public interface OrderRepository extends JpaRepository<Order,Long>{
//	Order findOrderByUser(User user);

	Order findOrderByOrderidAndUser(Long orderid,Optional<User> useroptional);
}
