package me.dakbutfly.service;

import com.google.common.base.Strings;
import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.domain.Order;
import me.dakbutfly.domain.OrderLine;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.repository.MemberRepository;
import me.dakbutfly.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khk on 2017-01-16.
 */
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

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

    public Order order(Member member, Item item, String address) throws DataValidateExption {
        Order order = new Order();
        order.setMember(member);
        OrderLine orderLine = new OrderLine();
        orderLine.setItem(item);
        order.setOrderLine(orderLine);
        order.setAddress(address);
        registerOrder(order);
        return order;
    }
}
