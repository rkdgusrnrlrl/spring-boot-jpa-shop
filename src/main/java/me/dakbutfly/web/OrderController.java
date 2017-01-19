package me.dakbutfly.web;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.service.ItemService;
import me.dakbutfly.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by khk on 2017-01-16.
 */
@Controller
public class OrderController {
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orderList(Model model) {
        System.out.println("/orders");
        return "orders/orderList";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String creatOrderForm(Model model) {
        List<Member> members = memberService.findAllMember();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "orders/orderForm";
    }

   /* @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(Model model) {
        List<Member> members = memberService.findAllMember();
        List<Item> items = itemService.findAllItems();
        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "orders/orderForm";
    }*/
}
