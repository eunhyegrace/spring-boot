package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberMemoryRepositoryTest { // 테스트를 위해서 사용하는 것으로 public으로 하지 않아도 됨

    MemberMemoryRepository repository = new MemberMemoryRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 각 테스트가 끝날때마다
    }


    @Test
    public void save() {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Optional에서 값을 꺼낼 때는 .get()으로 꺼낼 수 있음
        //Assertions.assertEquals(member, result);
        //assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // 레파지토리에 멤버 저장

//spring1, 2 회원이 저장이 된 형태


        //find로 회원을 찾기
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1); //find로 회원2가 찾아지면서 실행시면 정상동작 확인가능


    }
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //repository.findAll(); / repository에 커서 놓고 alt+enter 누르면 자동으로 아래코드 생성
        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
