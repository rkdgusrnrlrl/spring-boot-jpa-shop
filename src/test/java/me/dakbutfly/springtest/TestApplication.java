package me.dakbutfly.springtest;

import com.google.common.base.Strings;
import me.dakbutfly.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by dakbutfly on 2017-01-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class TestApplication {


    @PersistenceContext
    EntityManager em;

    @Test
    public void Member_등록_후_번호를_반환해야함() throws Exception {

        //Member 등록후 꺼내기
        Member member = new Member();
        member.setId("rkdgusrnrlrl");
        member.setName("강현구");

        Long no = register(member);

        assertThat(em.find(Member.class, no), is(member));
    }

    @Test
    public void 아이디로_회원을_찾을수_있어야함() throws Exception {
        //given
        Member member = new Member();
        String id = "rkdgusrnrlrl";
        member.setId(id);
        member.setName("강현구");

        Long no = register(member);
        //when
        Member findMember = findMemberById(id);
        assertThat(findMember, is(member));
    }


    @Test(expected = Exception.class)
    public void 중복_아이디_등록시_DuplicateIdException_발생() throws Exception {
        //given
        Member member = new Member();
        String id = "rkdgusrnrlrl";
        member.setId(id);
        member.setName("강현구");
        register(member);

        Member member1 = new Member();
        member1.setId(id);
        member1.setName("강현구");
        register(member1);

        fail("예외가 발생하지 않았습니다.");
    }

    @Test(expected = Exception.class)
    public void 등록시_아이디_없을시_예외발생() throws Exception {
        //given
        Member member1 = new Member();

        //when
        register(member1);

        //assert
        fail("예외가 발생하지 않았습니다.");
    }

    @Test
    public void 전체_회원_반환() throws Exception {
        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setId("id"+i);
            member.setName("강현구"+i);
            register(member);
        }

        List<Member> members = findAllMember();
        assertNotNull(members);
        assertThat(members.size(), is(10));
    }

    @Test
    public void 빈값에_전체회원_반환시_빈리스트_반환() throws Exception {
        List<Member> allMember = findAllMember();
        assertNotNull(allMember);
        assertTrue(allMember.isEmpty());
        assertThat(allMember.size(), is(0));
    }

    private List<Member> findAllMember() {
        List<Member> members = em.createQuery("select  m from Member m", Member.class).getResultList();
        return members;
    }


    private Long register(Member member) throws Exception {
        String id = member.getId();
        if (Strings.isNullOrEmpty(id)) throw new Exception("아이디가 없습니다.");
        Member findMember = findMemberById(id);
        if (findMember != null) throw new Exception("중복된 아이디입니다.");
        em.persist(member);
        return member.getNo();
    }

    private Member findMemberById(String id) {
        try {
            Member findMember = em.createQuery("select m from Member m where m.id = :id", Member.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return findMember;
        } catch (NoResultException e) {
            return null;
        }
    }
}
