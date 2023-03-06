package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.FindPasswordEntityRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.application.exception.InvalidLinkException;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import com.clanone.onedayclan.member.application.port.out.*;
import com.clanone.onedayclan.member.domain.Member;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MemberAdapter implements SaveMemberPort, GetMemberPort, FindUserIdPort, CheckEmailPort, UpdateMemberInfoPort {

    private final MemberEntityRepository memberEntityRepository;
    private final FindPasswordEntityRepository findPasswordEntityRepository;

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
    public FindPasswordEntity findMemberByAuthorizationCode(String authorizationCode) {
        FindPasswordEntity findPasswordMember = findPasswordEntityRepository.findByAuthorizationCode(authorizationCode).orElseThrow(() -> {
            throw new MemberNotFoundException();
        });

        if (findPasswordMember.getValidAt().isBefore(LocalDateTime.now())) {
            throw new InvalidLinkException("유효시간이 지났습니다.");
        }

        if (findPasswordMember.getUsedYn()){
            throw new InvalidLinkException("이미 사용한 링크입니다.");
        }

        return findPasswordMember;
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

    @Override
    public void resetPassword(String email, String password) {
        MemberEntity member = memberEntityRepository.findByUserId(email).orElseThrow(() -> {
            throw new MemberNotFoundException();
        });
        member.changePassword(password);
    }
}
