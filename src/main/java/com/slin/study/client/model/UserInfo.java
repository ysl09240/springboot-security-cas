package com.slin.study.client.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 14:37
 * Description
 */
@Entity
@Table(name = "t_user")
public class UserInfo implements UserDetails {
    private static final long serialVersionUID = -1041327031937199938L;

    /**
     * 用户ID
     */
    @Id
    private Long id;

    /**
     * 用户名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 登录名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 登录密码
     */
    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Transient
    List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities.size()==0){
            if (!CollectionUtils.isEmpty(roles)){
                for (Role role:roles){
                    List<Resource> resources = role.getResources();
                    if (!CollectionUtils.isEmpty(resources)){
                        for (Resource resource:resources){
                            grantedAuthorities.add(new SimpleGrantedAuthority(resource.getResCode()));
                        }
                    }
                }
            }
            grantedAuthorities.add(new SimpleGrantedAuthority("AUTH_0"));
        }
        return grantedAuthorities;
    }
}

