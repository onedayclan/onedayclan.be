package com.clanone.onedayclan.customer.adapter.out.adapter;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.model.TogetherClassSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.TogetherClassRepository;
import com.clanone.onedayclan.customer.application.exception.TogetherClassNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetTogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.ManageTogetherClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TogetherClassAdapter implements ManageTogetherClassPort, GetTogetherClassPort {

    private final TogetherClassRepository togetherClassRepository;

    @Override
    public void insertClassTogether(TogetherClassEntity togetherClassEntity) {
        togetherClassRepository.save(togetherClassEntity);
    }

    @Override
    public List<TogetherClassEntity> getTogetherClassList(String userId) {
        return togetherClassRepository.findByMemberUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public TogetherClassEntity getTogetherClassDetail(String userId, long seq) {
        return togetherClassRepository.findByMemberUserIdAndSeq(userId, seq).orElseThrow(() -> {
            throw new TogetherClassNotFoundException();
        });
    }

    @Override
    public Page<AdminTogetherClassResponse> getTogetherListForAdmin(TogetherClassSearchModel model, Pageable pageable) {
        return togetherClassRepository.getTogetherListForAdmin(model, pageable);
    }

    @Override
    public AdminTogetherClassDetailResponse getTogetherForAdmin(long togetherClassSeq) {
        return togetherClassRepository.getTogetherForAdmin(togetherClassSeq);
    }

    @Override
    public TogetherClassEntity getTogetherClassById(long togetherClassSeq) {
        return togetherClassRepository.findById(togetherClassSeq).orElseThrow(() -> {throw new TogetherClassNotFoundException();});
    }
}
