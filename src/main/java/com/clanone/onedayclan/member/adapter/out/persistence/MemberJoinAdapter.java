package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MemberJoinAdapter implements SaveMemberPort {

    private final MemberEntityRepository memberEntityRepository;

    @Transactional
    public void joinMember(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                                .id(member.getId())
                                .password(member.getPassword())
                                .name(member.getName())
                                .email(member.getEmail())
                                .phone(member.getPhone())
                                .build();
        memberEntityRepository.save(memberEntity);
    }
}
