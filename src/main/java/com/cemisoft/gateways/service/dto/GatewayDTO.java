package com.cemisoft.gateways.service.dto;

import java.io.Serializable;

public class GatewayDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GatewayDTO)) return false;
        GatewayDTO that = (GatewayDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "GatewayDTO{" +
                "id=" + id +
                '}';
    }
}
