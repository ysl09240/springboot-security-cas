package com.slin.study.client.service;

import com.slin.study.client.dao.ResourceDao;
import com.slin.study.client.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 15:55
 * Description
 */
@Service
public class ResourceService {
    @Autowired
    private ResourceDao resourceDao;

    public List<Resource> getAll(){
        return resourceDao.getAllResource();
    }
}