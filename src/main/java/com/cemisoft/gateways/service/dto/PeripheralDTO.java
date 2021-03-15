package com.cemisoft.gateways.service.dto;

import com.cemisoft.gateways.domain.enumeration.Status;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PeripheralDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String vendor;

    private Long createdDate;

    private Status status;

    private Long gatewayId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeripheralDTO)) return false;
        PeripheralDTO that = (PeripheralDTO) o;
        return Objects.equals(getVendor(), that.getVendor()) &&
                Objects.equals(getCreatedDate(), that.getCreatedDate()) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVendor(), getCreatedDate(), status);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(Long gatewayId) {
        this.gatewayId = gatewayId;
    }
}
