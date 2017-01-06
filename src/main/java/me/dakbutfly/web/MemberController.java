package me.dakbutfly.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by khk on 2017-01-05.
 */
@Controller
public class MemberController {

    @RequestMapping("/members/new")
    public String creatForm(Model model){
        return "members/creatMemberForm";
    }

}
