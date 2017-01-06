package me.dakbutfly.web;

import me.dakbutfly.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by khk on 2017-01-05.
 */
@Controller
public class MemberController {


    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String creatForm(Model model){
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String creat(Model model, Member member){
        System.out.println("MEMBEER="+member);

        return "members/createMemberForm";
    }
}
