package com.hyecheon.springsecuritystudy.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ACCESS_IP")
public class AccessIp implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "IP_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;

    public AccessIp(Long id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
    }

    public AccessIp() {
    }

    public static AccessIpBuilder builder() {
        return new AccessIpBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String toString() {
        return "AccessIp(id=" + this.id + ", ipAddress=" + this.ipAddress + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AccessIp)) return false;
        final AccessIp other = (AccessIp) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AccessIp;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public static class AccessIpBuilder {
        private Long id;
        private String ipAddress;

        AccessIpBuilder() {
        }

        public AccessIp.AccessIpBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccessIp.AccessIpBuilder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public AccessIp build() {
            return new AccessIp(id, ipAddress);
        }

        public String toString() {
            return "AccessIp.AccessIpBuilder(id=" + this.id + ", ipAddress=" + this.ipAddress + ")";
        }
    }
}