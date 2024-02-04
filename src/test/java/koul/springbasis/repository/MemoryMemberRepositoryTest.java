package koul.springbasis.repository;

import koul.springbasis.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  //순서에 의존을 하지 않도록 설정
  @AfterEach
  public void afterEach(){
    repository.clearStore();
  }

  @Test
  public void save(){
    Member member = new Member();
    member.setName("spring");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();
    Assertions.assertEquals(member ,result);
  }

  @Test
  public void findByName(){
    Member m1 = new Member();
    m1.setName("spring1");
    repository.save(m1);

    Member m2 = new Member();
    m2.setName("spring2");
    repository.save(m2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(m1);
  }

  @Test
  public void findAll(){
    Member m1 = new Member();
    m1.setName("spring1");
    repository.save(m1);

    Member m2 = new Member();
    m2.setName("spring2");
    repository.save(m2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }

}
