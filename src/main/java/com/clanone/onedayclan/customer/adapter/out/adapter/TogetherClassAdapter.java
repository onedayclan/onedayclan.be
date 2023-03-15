package com.clanone.onedayclan.customer.adapter.out.adapter;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.TogetherClassRepository;
import com.clanone.onedayclan.customer.application.exception.TogetherClassNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetTogetherClassPort;
import com.clanone.onedayclan.customer.application.port.out.ManageTogetherClassPort;
import lombok.RequiredArgsConstructor;
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
}
