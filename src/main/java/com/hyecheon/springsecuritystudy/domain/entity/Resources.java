package com.hyecheon.springsecuritystudy.domain.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RESOURCES")
@EntityListeners(value = {AuditingEntityListener.class})
public class Resources implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "resource_id")
    private Long id;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "resource_type")
    private String resourceType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_resources", joinColumns = {
            @JoinColumn(name = "resource_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet = new HashSet<>();

    public Resources(Long id, String resourceName, String httpMethod, int orderNum, String resourceType, Set<Role> roleSet) {
        this.id = id;
        this.resourceName = resourceName;
        this.httpMethod = httpMethod;
        this.orderNum = orderNum;
        this.resourceType = resourceType;
        this.roleSet = roleSet;
    }

    public Resources() {
    }

    public static ResourcesBuilder builder() {
        return new ResourcesBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public int getOrderNum() {
        return this.orderNum;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public Set<Role> getRoleSet() {
        return this.roleSet;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Resources)) return false;
        final Resources other = (Resources) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        return this$id == null ? other$id == null : this$id.equals(other$id);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Resources;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "Resources(id=" + this.getId() + ", resourceName=" + this.getResourceName() + ", httpMethod=" + this.getHttpMethod() + ", orderNum=" + this.getOrderNum() + ", resourceType=" + this.getResourceType() + ")";
    }

    public static class ResourcesBuilder {
        private Long id;
        private String resourceName;
        private String httpMethod;
        private int orderNum;
        private String resourceType;
        private Set<Role> roleSet;

        ResourcesBuilder() {
        }

        public Resources.ResourcesBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Resources.ResourcesBuilder resourceName(String resourceName) {
            this.resourceName = resourceName;
            return this;
        }

        public Resources.ResourcesBuilder httpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Resources.ResourcesBuilder orderNum(int orderNum) {
            this.orderNum = orderNum;
            return this;
        }

        public Resources.ResourcesBuilder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public Resources.ResourcesBuilder roleSet(Set<Role> roleSet) {
            this.roleSet = roleSet;
            return this;
        }

        public Resources build() {
            return new Resources(id, resourceName, httpMethod, orderNum, resourceType, roleSet);
        }

        public String toString() {
            return "Resources.ResourcesBuilder(id=" + this.id + ", resourceName=" + this.resourceName + ", httpMethod=" + this.httpMethod + ", orderNum=" + this.orderNum + ", resourceType=" + this.resourceType + ", roleSet=" + this.roleSet + ")";
        }
    }
}