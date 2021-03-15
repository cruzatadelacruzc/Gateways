package com.cemisoft.gateways.service.dto;

import java.io.Serializable;
import java.util.Objects;

public class GatewayDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String serialNumber;

    private String name;

    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GatewayDTO)) return false;
        GatewayDTO that = (GatewayDTO) o;
        return Objects.equals(getSerialNumber(), that.getSerialNumber()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getName(), getAddress());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
