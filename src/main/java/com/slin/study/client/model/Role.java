package com.slin.study.client.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 14:38
 * Description
 */
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_role_res",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "res_id")
    )
    private List<Resource> resources;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_user_role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserInfo> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}