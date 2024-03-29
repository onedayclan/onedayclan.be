package com.clanone.onedayclan.member.adapter.out.persistence.adapter;

import com.clanone.onedayclan.member.adapter.in.web.response.*;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.FindPasswordEntityRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityCustomRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.OrganizationEntityRepository;
import com.clanone.onedayclan.member.application.exception.InvalidAccessException;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import com.clanone.onedayclan.member.application.exception.OrganizationNotFoundException;
import com.clanone.onedayclan.member.application.port.out.*;
import com.clanone.onedayclan.member.domain.Member;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MemberAdapter implements SaveMemberPort, GetMemberPort, CheckEmailPort, UpdateMemberInfoPort, CheckMemberInfoPort{

    private final MemberEntityRepository memberEntityRepository;
    private final FindPasswordEntityRepository findPasswordEntityRepository;
    private final MemberEntityCustomRepository memberEntityCustomRepository;
    private final OrganizationEntityRepository organizationEntityRepository;

    @Transactional
    public void joinMember(Member member) {
        MemberEntity memberEntity = MemberEntity.builder()
                                .userId(member.getId())
                                .password(member.getPassword())
                                .name(member.getName())
                                .phone(member.getPhone())
                                .requestOrganization(organizationEntityRepository.findById(member.getOrganization()).orElseThrow(() -> {throw new OrganizationNotFoundException();}))
                                .organizationStatus(MemberOrganizationStatus.WAITING)
                                .type(MemberType.NORMAL)
                                .build();
        memberEntityRepository.save(memberEntity);
    }

    @Override
    public MemberEntity saveMember(MemberEntity member) {
        return memberEntityRepository.save(member);
    }

    @Override
    public void saveFindPassword(String email, String authorizationCode) {
        FindPasswordEntity findPassword = FindPasswordEntity.builder()
                .email(email)
                .authorizationCode(authorizationCode)
                .validAt(LocalDateTime.now().plusMinutes(5L))
                .build();
        findPasswordEntityRepository.save(findPassword);
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
        return memberEntityCustomRepository.findMemberList(searchModel, pageable);
    }

    @Override
    public Page<MemberSearchResponse> searchOrganizationMemberList(MemberSearchModel searchModel, Pageable pageable) {
        return memberEntityCustomRepository.findOrganizationMemberList(searchModel, pageable);
    }

    @Override
    public MemberEntity findMember(long memberSeq) {
         return memberEntityRepository.findById(memberSeq).orElseThrow(() -> {throw new MemberNotFoundException();});
    }

    @Override
    public List<OrganizationResponse> getOrganizationList() {
        List<OrganizationEntity> all = organizationEntityRepository.findAll();
        return all.stream()
                .map(OrganizationResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationMemberDetailResponse getOrganizationMember(long memberSeq) {
        MemberEntity member = memberEntityRepository.findById(memberSeq)
                .orElseThrow(() -> {throw new MemberNotFoundException();});
        long organizationMemberCount = memberEntityRepository.countByConfirmOrganizationSeqAndType(member.getConfirmOrganization().getSeq(), MemberType.NORMAL);
        return OrganizationMemberDetailResponse.of(member, organizationMemberCount);
    }

    @Override
    public long countMemberByOrganizationSeq(long organizationSeq) {
        return memberEntityRepository.countByConfirmOrganizationSeqAndType(organizationSeq, MemberType.NORMAL);
    }

    @Override
    public Page<OrganizationConfirmResponse> getOrganizationConfirmList(Pageable pageable) {
        Page<MemberEntity> confirmList = memberEntityRepository.findByOrganizationStatus(MemberOrganizationStatus.WAITING, pageable);
        return new PageImpl<>(confirmList.getContent().stream().map(OrganizationConfirmResponse::of).collect(Collectors.toList()), pageable, confirmList.getTotalElements());
    }

    @Override
    public MemberEntity getMemberByUserId(String userId) {
        return memberEntityRepository.findByUserId(userId).orElseThrow(() -> {
            throw new MemberNotFoundException();
        });
    }

    @Override
    public MemberEntity getMemberByUserIdAndNameAndPhone(String userId, String name, String phone) {
        return memberEntityRepository.findByUserIdAndNameAndPhone(userId, name, phone)
                .orElseThrow(() -> {throw new MemberNotFoundException();});
    }

    @Override
    public String getUserId(String name, String phone) {
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
