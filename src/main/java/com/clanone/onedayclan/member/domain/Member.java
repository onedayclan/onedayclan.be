package com.clanone.onedayclan.member.domain;

public class Member {
    private String id;

    private String name;

    private String password;

    private long organizationSeq;

    private String phone;

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public long getOrganization() {
        return this.organizationSeq;
    }

    public String getPhone() {
        return this.phone;
    }

    public Member(String id, String name, String password, long organizationSeq, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.organizationSeq = organizationSeq;
        this.phone = phone;
    }
}
