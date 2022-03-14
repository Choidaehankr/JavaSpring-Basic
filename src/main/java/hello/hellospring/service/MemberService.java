package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service

@Transactional
public class MemberService {
    // 회원 가입
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;  // DI

    @Autowired
    public MemberService(MemberRepository memberRepository) {  // DI
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        // 조건1. 같은 이름이 있으면 안된다.
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
//        memberRepository.findByName(member.getName()).
//                ifPresent(m-> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); 같은 코드
        // 뭐 ctrl+T해서 위 중복회원 검증 코드를 validateDuplicateMember란 이름으로 축소시켰는데 검색해보자.
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
