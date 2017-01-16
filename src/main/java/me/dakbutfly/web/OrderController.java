package me.dakbutfly.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by khk on 2017-01-16.
 */
public class OrderController {

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orderList(Model model) {
        return "";
    }
}
