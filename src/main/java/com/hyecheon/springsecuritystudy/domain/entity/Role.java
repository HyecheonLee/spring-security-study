package com.hyecheon.springsecuritystudy.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    @OrderBy("orderNum desc")
    private Set<Resources> resourcesSet = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userRoles")
    private Set<Account> accounts = new HashSet<>();

    public Role(Long id, String roleName, String roleDesc, Set<Resources> resourcesSet, Set<Account> accounts) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.resourcesSet = resourcesSet;
        this.accounts = accounts;
    }

    public Role() {
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public String getRoleDesc() {
        return this.roleDesc;
    }

    public Set<Resources> getResourcesSet() {
        return this.resourcesSet;
    }

    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public void setResourcesSet(Set<Resources> resourcesSet) {
        this.resourcesSet = resourcesSet;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) return false;
        final Role other = (Role) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Role;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "Role(id=" + this.getId() + ", roleName=" + this.getRoleName() + ", roleDesc=" + this.getRoleDesc() + ", accounts=" + this.getAccounts() + ")";
    }

    public static class RoleBuilder {
        private Long id;
        private String roleName;
        private String roleDesc;
        private Set<Resources> resourcesSet;
        private Set<Account> accounts;

        RoleBuilder() {
        }

        public Role.RoleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Role.RoleBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Role.RoleBuilder roleDesc(String roleDesc) {
            this.roleDesc = roleDesc;
            return this;
        }

        public Role.RoleBuilder resourcesSet(Set<Resources> resourcesSet) {
            this.resourcesSet = resourcesSet;
            return this;
        }

        public Role.RoleBuilder accounts(Set<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public Role build() {
            return new Role(id, roleName, roleDesc, resourcesSet, accounts);
        }

        public String toString() {
            return "Role.RoleBuilder(id=" + this.id + ", roleName=" + this.roleName + ", roleDesc=" + this.roleDesc + ", resourcesSet=" + this.resourcesSet + ", accounts=" + this.accounts + ")";
        }
    }
}