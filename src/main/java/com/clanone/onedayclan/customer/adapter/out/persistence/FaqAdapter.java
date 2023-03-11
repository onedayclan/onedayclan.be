package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminFaqResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.adapter.out.model.FaqSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.FaqRepository;
import com.clanone.onedayclan.customer.application.FaqNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetFaqPort;
import com.clanone.onedayclan.customer.application.port.out.ManageFaqPort;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationConfirmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FaqAdapter implements GetFaqPort, ManageFaqPort {

    private final FaqRepository faqRepository;

    @Override
    public List<FaqEntity> getFaqList() {
        return faqRepository.findByShowYnOrderByCreatedAtAsc(true);
    }

    @Override
    public Page<AdminFaqResponse> getFaqListForAdmin(FaqSearchModel model, Pageable pageable) {
        Page<FaqEntity> faqList = faqRepository.getFaqListForAdmin(model, pageable);
        return new PageImpl<>(faqList.getContent().stream().map(AdminFaqResponse::of).collect(Collectors.toList()), pageable, faqList.getTotalElements());
    }

    @Override
    public FaqEntity getFaq(long faqSeq) {
        return faqRepository.findById(faqSeq).orElseThrow(() -> {throw new FaqNotFoundException();});
    }

    @Override
    public FaqEntity saveFaq(FaqEntity entity) {
        return faqRepository.save(entity);
    }

    @Override
    public void deleteFaq(long faqSeq) {
        faqRepository.deleteById(faqSeq);
    }
}
