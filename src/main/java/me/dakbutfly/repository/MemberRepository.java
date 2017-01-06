package me.dakbutfly.repository;

import me.dakbutfly.domain.Member;
import org.springframework.data.repository.Repository;

import java.util.List;


/**
 * Created by khk on 2017-01-06.
 */
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);
    Member findById(String id);
    Member findByNo(Long no);
    List<Member> findAll();
}
