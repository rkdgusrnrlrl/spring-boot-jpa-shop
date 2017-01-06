package me.dakbutfly.service;

import com.google.common.base.Strings;
import me.dakbutfly.domain.Member;
import me.dakbutfly.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberService() {
    }

    public Member findMemberByNo(Long no) {
        return memberRepository.findByNo(no);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    public Long register(Member member) throws IllegalStateException {
        valdationMember(member);
        memberRepository.save(member);
        return member.getNo();
    }

    private void valdationMember(Member member) throws IllegalStateException {
        String id = member.getId();
        if (Strings.isNullOrEmpty(id)) throw new IllegalStateException("아이디가 없습니다.");
        Member findMember = findMemberById(id);
        if (findMember != null) throw new IllegalStateException("중복된 아이디입니다.");
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