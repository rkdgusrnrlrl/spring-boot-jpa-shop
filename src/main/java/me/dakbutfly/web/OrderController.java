package me.dakbutfly.web;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.domain.Order;
import me.dakbutfly.exception.DataValidateExption;
import me.dakbutfly.service.ItemService;
import me.dakbutfly.service.MemberService;
import me.dakbutfly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orderList(Model model) {
        System.out.println("/orders");
        List<Order> orders = orderService.findOrders();
        model.addAttribute("orders", orders);
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

   @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String order(@RequestParam("memberId") String memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("address") String address,
                        Model model) {
       Member memberById = memberService.findMemberById(memberId);
       Item itemById = itemService.findItemById(itemId);
       try {
           orderService.order(memberById, itemById, address);
       } catch (DataValidateExption dataValidateExption) {
           dataValidateExption.printStackTrace();
       }
       return "redirect:/orders";
    }
}
