package com.slin.study.client.config;

import com.slin.study.client.model.Resource;
import com.slin.study.client.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 16:03
 * Description
 */
@Component
public class SecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceService resourceService;

    private LinkedHashMap<String,Collection<ConfigAttribute>> metaData;
    @PostConstruct
    private void loadSecurityMetaData(){
        List<Resource> list = resourceService.getAll();
        metaData=new LinkedHashMap<>();
        for (Resource resource:list){
            List<ConfigAttribute> attributes=new ArrayList<>();
            attributes.add(new SecurityConfig(resource.getResCode()));
            metaData.put(resource.getUrl(),attributes);
        }
        List<ConfigAttribute> base=new ArrayList<>();
        base.add(new SecurityConfig("AUTH_0"));
        metaData.put("/**",base);
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation invocation= (FilterInvocation) object;
        if (metaData==null){
            return new ArrayList<>(0);
        }
        String requestUrl = invocation.getRequestUrl();
        System.out.println("请求Url："+requestUrl);
        Iterator<Map.Entry<String, Collection<ConfigAttribute>>> iterator = metaData.entrySet().iterator();
        Collection<ConfigAttribute> rs=new ArrayList<>();
        while (iterator.hasNext()){
            Map.Entry<String, Collection<ConfigAttribute>> next = iterator.next();
            String url = next.getKey();
            Collection<ConfigAttribute> value = next.getValue();
            RequestMatcher requestMatcher=new AntPathRequestMatcher(url);
            if (requestMatcher.matches(invocation.getRequest())){
                rs = value;
                break;
            }
        }
        System.out.println("拦截认证权限为："+rs);
        return rs;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}