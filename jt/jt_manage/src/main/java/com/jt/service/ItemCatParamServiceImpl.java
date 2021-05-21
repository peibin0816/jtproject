package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatParamMapper;
import com.jt.pojo.ItemCatParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemCatParamServiceImpl implements ItemCatParamService{

    @Autowired
    private ItemCatParamMapper itemCatParamMapper;

    @Override
    public List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam) {
        QueryWrapper queryWrapper = new QueryWrapper(itemCatParam);
        return itemCatParamMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public int addItemCatParam(ItemCatParam itemCatParam) {
        return itemCatParamMapper.insert(itemCatParam);
    }

    @Override
    @Transactional
    public int updateItemCatParam(ItemCatParam itemCatParam) {
        return itemCatParamMapper.updateById(itemCatParam);
    }

    @Override
    public int deleteItemCatParamById(Integer paramId) {
        return itemCatParamMapper.deleteById(paramId);
    }
}
