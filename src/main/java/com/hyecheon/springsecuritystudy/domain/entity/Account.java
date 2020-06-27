package com.hyecheon.springsecuritystudy.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private int age;

    @Column
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "account_roles", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private Set<Role> userRoles = new HashSet<>();

    public Account(Long id, String username, String email, int age, String password, Set<Role> userRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Account() {
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Role> getUserRoles() {
        return this.userRoles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRoles(Set<Role> userRoles) {
        this.userRoles = userRoles;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Account)) return false;
        final Account other = (Account) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (!Objects.equals(this$id, other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Account;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }

    public String toString() {
        return "Account(id=" + this.getId() + ", username=" + this.getUsername() + ", email=" + this.getEmail() + ", age=" + this.getAge() + ", password=" + this.getPassword() + ")";
    }

    public static class AccountBuilder {
        private Long id;
        private String username;
        private String email;
        private int age;
        private String password;
        private Set<Role> userRoles;

        AccountBuilder() {
        }

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AccountBuilder age(int age) {
            this.age = age;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder userRoles(Set<Role> userRoles) {
            this.userRoles = userRoles;
            return this;
        }

        public Account build() {
            return new Account(id, username, email, age, password, userRoles);
        }

        public String toString() {
            return "Account.AccountBuilder(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", age=" + this.age + ", password=" + this.password + ", userRoles=" + this.userRoles + ")";
        }
    }
}
