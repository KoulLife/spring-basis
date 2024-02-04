package koul.springbasis.service;

import koul.springbasis.domain.Member;
import koul.springbasis.repository.MemberRepository;
import koul.springbasis.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach
  public void beforeEach(){
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach
  public void afterEach(){
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    //given
    Member member = new Member();
    member.setName("hello");

    //when
    Long savedId = memberService.join(member);

    //then
    Member findMember = memberService.findOne(savedId).get();
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void 중복_회원_예외(){
    //given
    Member member1 = new Member();
    member1.setName("hello");

    Member member2 = new Member();
    member2.setName("hello");

    //when
    memberService.join(member1);
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//    try {
//      memberService.join(member2);
//      fail();
//    }catch (IllegalStateException e){
//      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }

    //then
  }

  @Test
  void 회원_전체찾기() {
  }

  @Test
  void 회원_한명찾기() {
  }
}