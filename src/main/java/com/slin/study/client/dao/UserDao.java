package com.slin.study.client.dao;

import com.slin.study.client.model.Resource;
import com.slin.study.client.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 15:52
 * Description
 */
@Repository
public interface UserDao extends JpaRepository<UserInfo,String> {
    @Override
    List<UserInfo> findAll();

    List<UserInfo> findByUsername(String username);

    /**
     * 根据用户名like查询
     * @param username
     * @return
     */
    List<UserInfo> getUserByUsernameContains(String username);

    @Query("from UserInfo where id=:id")
    UserInfo getUserById(@Param("id") String id);

}
