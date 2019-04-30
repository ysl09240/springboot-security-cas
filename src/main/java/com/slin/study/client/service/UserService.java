package com.slin.study.client.service;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 15:51
 * Description
 */

import com.slin.study.client.dao.UserDao;
import com.slin.study.client.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserInfo findByUsername(String username){
        List<UserInfo> list = userDao.findByUsername(username);
        return list!=null&&list.size()>0?list.get(0):null;
    }
}