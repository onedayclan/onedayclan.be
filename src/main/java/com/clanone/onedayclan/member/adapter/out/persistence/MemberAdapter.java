package com.clanone.onedayclan.member.adapter.out.persistence;

import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.FindPasswordEntityRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityCustomRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.application.exception.InvalidAccessException;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import com.clanone.onedayclan.member.application.port.out.*;
import com.clanone.onedayclan.member.domain.Member;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MemberAdapter implements SaveMemberPort, GetMemberPort, FindUserIdPort, CheckEmailPort, UpdateMemberInfoPort, CheckMemberInfoPort{

    private final MemberEntityRepository memberEntityRepository;
    private final FindPasswordEntityRepository findPasswordEntityRepository;
    private final MemberEntityCustomRepository memberEntityCustomRepository;

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
            throw new InvalidAccessException("해당 회원을 찾을 수 없습니다.");
        });

        if (findPasswordMember.getValidAt().isBefore(LocalDateTime.now())) {
            throw new InvalidAccessException("유효시간이 지났습니다.");
        }

        if (findPasswordMember.isUsedYn()){
            throw new InvalidAccessException("이미 사용한 링크입니다.");
        }

        return findPasswordMember;
    }

    @Override
    public Page<MemberSearchResponse> searchMemberList(MemberSearchModel searchModel, Pageable pageable) {
        return memberEntityCustomRepository.findMember(searchModel, pageable);
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

    @Override
    public boolean checkPassword(String userId, String password) {
        return memberEntityRepository.existsByUserIdAndPassword(userId, password);
    }
}
