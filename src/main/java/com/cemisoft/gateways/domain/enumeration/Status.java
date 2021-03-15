package com.cemisoft.gateways.domain.enumeration;

public enum Status {
    ONLINE("O"), OFFLINE("OF");

    private String code;

    private Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}