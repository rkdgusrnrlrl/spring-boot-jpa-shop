package me.dakbutfly.web;

import me.dakbutfly.domain.Item;
import me.dakbutfly.domain.Member;
import me.dakbutfly.service.ItemService;
import me.dakbutfly.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by khk on 2017-01-05.
 */
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    private Logger logger = LoggerFactory.getLogger(ItemController.class);

    @RequestMapping(value = "/items/new", method = RequestMethod.GET)
    public String creatForm(Model model){
        return "items/createItemForm";
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.POST)
    public String creat(Model model, Item item){
        System.out.println("items/new[POST]");
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String members(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}