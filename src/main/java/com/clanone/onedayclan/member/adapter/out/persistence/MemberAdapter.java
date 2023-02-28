package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import com.clanone.onedayclan.member.application.port.out.CheckEmailPort;
import com.clanone.onedayclan.member.application.port.out.FindUserIdPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class MemberAdapter implements SaveMemberPort, GetMemberPort, FindUserIdPort, CheckEmailPort {

    private final MemberEntityRepository memberEntityRepository;

    @Transactional
    public void joinMember(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                                .userId(member.getId())
                                .password(member.getPassword())
                                .name(member.getName())
                                .phone(member.getPhone())
                                .organizationStatus(MemberOrganizationStatus.WAITING)
                                .type(MemberType.NORMAL)
                                .build();
        memberEntityRepository.save(memberEntity);
    }

    public Optional<MemberEntity> getMemberById(String id) {
        return memberEntityRepository.findByUserId(id);
    }

    @Override
    public String findUserId(String name, String phone) {
        MemberEntity member = memberEntityRepository.findByNameAndPhone(name, phone)
                                                    .orElseThrow(() -> {throw new MemberNotFoundException();});

        return member.getUserId();
    }

    @Override
    public boolean existsEmail(String email) {
        return memberEntityRepository.existsByUserId(email);
    }
}
