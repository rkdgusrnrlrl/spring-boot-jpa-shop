package me.dakbutfly.springtest;

import me.dakbutfly.domain.Member;
import me.dakbutfly.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by dakbutfly on 2017-01-04.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestApplication {

    @Autowired
    MemberService memberService;

    @Test
    public void Member_등록_후_번호를_반환해야함() throws Exception {

        //Member 등록후 꺼내기
        Member member = Fixture.getMemberFixture("rkdgusrnrlrl", "강현구");

        Long no = memberService.register(member);

        assertThat(memberService.findMemberByNo(no), is(member));
    }


    @Test
    public void 아이디로_회원을_찾을수_있어야함() throws Exception {
        //given
        Member member = new Member();
        String id = "rkdgusrnrlrl";
        member.setId(id);
        member.setName("강현구");

        Long no = memberService.register(member);
        //when
        Member findMember = memberService.findMemberById(id);
        assertThat(findMember, is(member));
    }


    @Test(expected = Exception.class)
    public void 중복_아이디_등록시_DuplicateIdException_발생() throws Exception {
        //given
        Member member = new Member();
        String id = "rkdgusrnrlrl";
        member.setId(id);
        member.setName("강현구");
        memberService.register(member);

        Member member1 = Fixture.getMemberFixture(id, "강현구");
        memberService.register(member1);

        fail("예외가 발생하지 않았습니다.");
    }

    @Test(expected = Exception.class)
    public void 등록시_아이디_없을시_예외발생() throws Exception {
        //given
        Member member1 = new Member();

        //when
        memberService.register(member1);

        //assert
        fail("예외가 발생하지 않았습니다.");
    }

    @Test
    public void 전체_회원_반환() throws Exception {
        for (int i = 0; i < 10; i++) {
            Member member = Fixture.getMemberFixture("id" + i, "강현구" + i);
            memberService.register(member);
        }

        List<Member> members = memberService.findAllMember();
        assertNotNull(members);
        assertThat(members.size(), is(10));
    }

    @Test
    public void 빈값에_전체회원_반환시_빈리스트_반환() throws Exception {
        List<Member> allMember = memberService.findAllMember();
        assertNotNull(allMember);
        assertTrue(allMember.isEmpty());
        assertThat(allMember.size(), is(0));
    }

    public Member findMemberByNo(Long no) {
        return memberService.findMemberByNo(no);
    }

    public List<Member> findAllMember() {
        return memberService.findAllMember();
    }


    public Long register(Member member) throws Exception {
        return memberService.register(member);
    }

    public Member findMemberById(String id) {
        return memberService.findMemberById(id);
    }
}
