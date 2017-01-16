package me.dakbutfly.service;

import com.google.common.base.Strings;
import me.dakbutfly.domain.Order;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khk on 2017-01-16.
 */
@Service
public class OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void registerOrder(Order order) throws DataValidateExption {
        if (order.getMember() == null) throw new DataValidateExption("member is null");
        if (order.getOrderLine() == null) throw new DataValidateExption("orderLine is null");
        if (order.getOrderLine().getItem() == null) throw new DataValidateExption("orderLine not contain item");
        if (Strings.isNullOrEmpty(order.getAddress())) throw new DataValidateExption("order's address is empty ");
        orderRepository.save(order);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }
}
