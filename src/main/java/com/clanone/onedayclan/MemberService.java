package com.clanone.onedayclan;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    /*private final MemberRepository memberRepository;

    public Member getMember(){
        return memberRepository.findById(1L).get();
    }

    public List<Member> getMemberList(){
        return memberRepository.findAll();
    }

    public void saveMember(){
        Member member = new Member();
        member.setName("신지원");

        memberRepository.save(member);
    }

    public Page<Member> getMemberListPaging(){
        return memberRepository.findAll(PageRequest.of(1, 2));
    }

    public Member throwNotFoundException(){
        throw new MemberNotFoundException(new Exception());
    }

    public Member throwBadRequestException() {
        throw new MemberAlreadyDeletedException(new Exception());
    }*/

}
