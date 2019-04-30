package com.slin.study.client.dao;

import com.slin.study.client.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 15:54
 * Description
 */
@Repository
public interface ResourceDao extends JpaRepository<Resource,String> {

    @Query("from Resource order by priority")
    List<Resource> getAllResource();
}