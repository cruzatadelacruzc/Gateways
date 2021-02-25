package com.cemisoft.gateways.domain;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

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

    @OneToMany(mappedBy = "gateway")
    private Set<Peripheral> peripherals;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", peripherals=" + peripherals +
                '}';
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

    public Set<Peripheral> getPeripherals() {
        return peripherals;
    }

    public void setPeripherals(Set<Peripheral> peripherals) {
        this.peripherals = peripherals;
    }

    public Gateway addPeripheral(Peripheral peripheral) {
        this.peripherals.add(peripheral);
        peripheral.setGateway(this);
        return this;
    }

    public Gateway removePeripheral(Peripheral peripheral) {
        this.peripherals.remove(peripheral);
        peripheral.setGateway(null);
        return this;
    }
}
