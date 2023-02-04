package com.clanone.onedayclan.member.domain;

public class Member {
    private String id;

    private String name;

    private String password;

    private String organization;

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

    public String getOrganization() {
        return this.organization;
    }

    public String getPhone() {
        return this.phone;
    }

    public Member(String id, String name, String password, String organization, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.organization = organization;
        this.phone = phone;
    }
}
