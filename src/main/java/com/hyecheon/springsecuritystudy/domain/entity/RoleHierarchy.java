package com.hyecheon.springsecuritystudy.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE_HIERARCHY")
public class RoleHierarchy implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "child_name")
    private String childName;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_name", referencedColumnName = "child_name")
    private RoleHierarchy parentName;

    @OneToMany(mappedBy = "parentName", cascade = {CascadeType.ALL})
    private Set<RoleHierarchy> roleHierarchy = new HashSet<RoleHierarchy>();

    public RoleHierarchy(Long id, String childName, RoleHierarchy parentName, Set<RoleHierarchy> roleHierarchy) {
        this.id = id;
        this.childName = childName;
        this.parentName = parentName;
        this.roleHierarchy = roleHierarchy;
    }

    public RoleHierarchy() {
    }

    public static RoleHierarchyBuilder builder() {
        return new RoleHierarchyBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getChildName() {
        return this.childName;
    }

    public RoleHierarchy getParentName() {
        return this.parentName;
    }

    public Set<RoleHierarchy> getRoleHierarchy() {
        return this.roleHierarchy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public void setParentName(RoleHierarchy parentName) {
        this.parentName = parentName;
    }

    public void setRoleHierarchy(Set<RoleHierarchy> roleHierarchy) {
        this.roleHierarchy = roleHierarchy;
    }

    public static class RoleHierarchyBuilder {
        private Long id;
        private String childName;
        private RoleHierarchy parentName;
        private Set<RoleHierarchy> roleHierarchy;

        RoleHierarchyBuilder() {
        }

        public RoleHierarchy.RoleHierarchyBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoleHierarchy.RoleHierarchyBuilder childName(String childName) {
            this.childName = childName;
            return this;
        }

        public RoleHierarchy.RoleHierarchyBuilder parentName(RoleHierarchy parentName) {
            this.parentName = parentName;
            return this;
        }

        public RoleHierarchy.RoleHierarchyBuilder roleHierarchy(Set<RoleHierarchy> roleHierarchy) {
            this.roleHierarchy = roleHierarchy;
            return this;
        }

        public RoleHierarchy build() {
            return new RoleHierarchy(id, childName, parentName, roleHierarchy);
        }

        public String toString() {
            return "RoleHierarchy.RoleHierarchyBuilder(id=" + this.id + ", childName=" + this.childName + ", parentName=" + this.parentName + ", roleHierarchy=" + this.roleHierarchy + ")";
        }
    }
}