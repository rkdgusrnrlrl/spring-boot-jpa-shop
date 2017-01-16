package me.dakbutfly.springtest;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;

public class Fixture {
    public static Member getMemberFixture(String rkdgusrnrlrl, String 강현구) {
        Member member = new Member();
        member.setId(rkdgusrnrlrl);
        member.setName(강현구);
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
}