package me.dakbutfly.web;

import me.dakbutfly.domain.Member;
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
public class MemberController {
    @Autowired
    private MemberService memberService;

    private Logger logger = LoggerFactory.getLogger(MemberController.class);

    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String creatForm(Model model){
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String creat(Model model, Member member){
        try {
            memberService.register(member);
        } catch (Exception e) {
            logger.error("creat", e);
        }
        return "redirect:/members";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String members(Model model){
        List<Member> allMember = memberService.findAllMember();
        model.addAttribute("members", allMember);
        return "members/memberList";
    }
}
