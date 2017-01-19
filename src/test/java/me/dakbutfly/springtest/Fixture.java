package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.domain.Order;
import me.dakbutfly.domain.OrderLine;

public class Fixture {
    public static Member getMemberFixture(String id, String name) {
        Member member = new Member();
        member.setId(id);
        member.setName(name);
        return member;
    }
    public static Member getMemberFixture() {
        Member member = new Member();
        member.setId("rkdgusrnrlrl");
        member.setName("강현구");
        return member;
    }

    public static Item getItemFixtrue() {
        Item item = new Item();
        item.setName("상품01");
        item.setPrice(1000);
        return item;
    }

    public static Order getOrderFixture(OrderLine orderLine, String address, Member member) {
        Order order1 = new Order();
        order1.setMember(member);
        order1.setOrderLine(orderLine);
        order1.setAddress(address);
        return order1;
    }

}