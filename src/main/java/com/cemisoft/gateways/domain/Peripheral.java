package com.cemisoft.gateways.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

enum Status {
    ONLINE, OFFLINE
}

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Peripheral implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String vendor;

    @CreatedDate
    private Long createdDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JsonIgnoreProperties(value = "peripherals", allowSetters = true)
    private Gateway gateway;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Peripheral{" +
                "id=" + id +
                ", vendor='" + vendor + '\'' +
                ", createdDate=" + createdDate +
                ", status=" + status +
                ", gateway=" + gateway +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Peripheral)) return false;
        Peripheral that = (Peripheral) o;
        return getId().equals(that.getId()) &&
                Objects.equals(getVendor(), that.getVendor()) &&
                Objects.equals(getCreatedDate(), that.getCreatedDate()) &&
                getStatus() == that.getStatus() &&
                Objects.equals(getGateway(), that.getGateway());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public void setGateway(Gateway gateway) {
        this.gateway = gateway;
    }
}
