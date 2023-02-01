package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.MemberNotFoundException;
import com.clanone.onedayclan.member.application.port.out.FindUserIdPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class MemberAdapter implements SaveMemberPort, GetMemberPort, FindUserIdPort {

    private final MemberEntityRepository memberEntityRepository;

    @Transactional
    public void joinMember(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                                .userId(member.getId())
                                .password(member.getPassword())
                                .name(member.getName())
                                .email(member.getEmail())
                                .phone(member.getPhone())
                                .build();
        memberEntityRepository.save(memberEntity);
    }

    public Optional<MemberEntity> getMemberById(String id) {
        return memberEntityRepository.findByUserId(id);
    }

    @Override
    public String findUserId(String name, String phone) {
        Optional<MemberEntity> findMember = memberEntityRepository.findByNameAndPhone(name,phone);
        MemberEntity member = findMember.orElseThrow(() -> {
            throw new MemberNotFoundException();
        });
        String userId = member.getUserId();

        return userId;
    }
}
