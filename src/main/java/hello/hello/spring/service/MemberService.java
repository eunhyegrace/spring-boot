package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberMemoryRepository;
import hello.hello.spring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // MemberService에 커서 두고 ctrl + shift + T(create new test) 입력 시 테스트 케이스 자동 생성

    private  final MemberRepository memberRepository = new MemberMemoryRepository();
    //회원 서비스가 있으려면 멤버 리포지토리가 있어야함

    /**
     *
     회원가입 */

    public Long join(Member member) {

        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();

        //Optional<Member> result = memberRepository.findByName(member.getName());
        // 멤버에 값이 있다면, ""해당 내용 출력하기 / optional 안에 null과 찾는 값이 모두 있음
        // 조건문으로 처리하는 것이 아닌 null값이 있을 수 있으면 optional로 감싸서 처리
        //Member member1 = result.get(); / 그냥 값을 꺼내고 싶으면 result.get()을 이용해서 꺼내면 됨


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 중복 확인하려는 것
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                }); // 회원가입을 할 때 중복이 있으면 exception이 터지는지 검증을 해야함 -> 테스트 케이스 이용

    }


    //전체 회원 조회하는 기능
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);

    }


}
