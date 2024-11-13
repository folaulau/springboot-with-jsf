package com.folau.jsf.entity.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.folau.jsf.entity.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@DynamicUpdate
@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "street2")
    private String street2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "country")
    private String country;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "timezone")
    private String timezone;

    @JsonIgnoreProperties(value = {"addresses","address"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true)
    private AddressType type;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public boolean isValidLatLong() {
        return this.latitude != null && this.longitude != null;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.id)
                .append(this.uuid)
                .append(this.name)
                .append(this.street)
                .append(this.street2)
                .append(this.city)
                .append(this.state)
                .append(this.zipcode)
                .append(this.country)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Address other = (Address) obj;
        return new EqualsBuilder().append(this.id, other.id)
                .append(this.uuid, other.uuid)
                .append(this.name, other.name)
                .append(this.street, other.street)
                .append(this.street2, other.street2)
                .append(this.city, other.city)
                .append(this.state, other.state)
                .append(this.zipcode, other.zipcode)
                .append(this.country, other.country)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @PrePersist
    private void preCreate() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = "address-" + UUID.randomUUID().toString().replaceAll("-", "");
        }

        if(type == null){
            type = AddressType.DELIVERY;
        }
    }

    @PreUpdate
    private void preUpdate() {
    }

}
