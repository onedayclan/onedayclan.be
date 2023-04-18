package com.clanone.onedayclan.clazz.domain.enums;

import lombok.Getter;

@Getter
public enum ClassListSort {
    NEW("최신순"),
    DISTANCE("거리순");

    private String value;

    ClassListSort(String value){
        this.value = value;
    }
}
