package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;

import java.util.List;

public interface ManageTogetherClassPort {
    void insertClassTogether(TogetherClassEntity togetherClassEntity);
}
