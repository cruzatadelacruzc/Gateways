package com.cemisoft.gateways.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * A client
 */
@Entity
public class Gateway implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serialNumber;

    private String name;

    @Pattern(regexp = "^([0-1]?\\d?\\d|2[0-4]\\d|25[0-5])(\\.([0-1]?\\d?\\d|2[0-4]\\d|25[0-5])){3}$")
    private String address;

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

    public Gateway serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gateway name(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gateway address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gateway)) return false;
        Gateway gateway = (Gateway) o;
        return Objects.equals(getSerialNumber(), gateway.getSerialNumber()) &&
                Objects.equals(getName(), gateway.getName()) &&
                Objects.equals(getAddress(), gateway.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSerialNumber(), getName(), getAddress());
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +/*
                ", peripherals=" + peripherals +*/
                '}';
    }
}
