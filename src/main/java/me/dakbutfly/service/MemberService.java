package me.dakbutfly.service;

import com.google.common.base.Strings;
import me.dakbutfly.domain.Member;
import me.dakbutfly.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MemberService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    public MemberService() {
    }

    public Member findMemberByNo(Long no) {
        return em.find(Member.class, no);
    }

    public List<Member> findAllMember() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Long register(Member member) throws Exception {
        String id = member.getId();
        if (Strings.isNullOrEmpty(id)) throw new Exception("아이디가 없습니다.");
        Member findMember = findMemberById(id);
        if (findMember != null) throw new Exception("중복된 아이디입니다.");
        memberRepository.save(member);
        return member.getNo();
    }

    public Member findMemberById(String id) {
        try {
            return memberRepository.findById(id);
        } catch (NoResultException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }
}