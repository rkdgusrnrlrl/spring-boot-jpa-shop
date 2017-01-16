package me.dakbutfly.repository;

import me.dakbutfly.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by khk on 2017-01-16.
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAll();
}
