package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RightsServiceImpl implements RightsService{

    @Autowired
    private RightsMapper rightsMapper;
    @Override
    public List<Rights> findall() {
        QueryWrapper<Rights> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",0);
        List<Rights> rights = rightsMapper.selectList(queryWrapper);
        for(Rights r:rights){
            queryWrapper.clear();
            queryWrapper.eq("parent_id",r.getId());
            List<Rights> list = rightsMapper.selectList(queryWrapper);
            r.setChildren(list);
        }
        return rights;
    }
}
