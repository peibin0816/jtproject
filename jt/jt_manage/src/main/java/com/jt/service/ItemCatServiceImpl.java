package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<ItemCat> findItemCatList(Integer type) {

        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        List<ItemCat> list = itemCatMapper.selectList(queryWrapper);
        if(list==null) return null;
        for(ItemCat itemCat:list){
            QueryWrapper<ItemCat> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("parent_id",itemCat.getId());
            List<ItemCat> twolist  = itemCatMapper.selectList(queryWrapper2);
            if(twolist==null) return null;
            for(ItemCat teoitemCat:list){
                QueryWrapper<ItemCat> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("parent_id",teoitemCat.getId());
                List<ItemCat> threelist  = itemCatMapper.selectList(queryWrapper3);
                teoitemCat.setChildren(threelist);
            }
            itemCat.setChildren(twolist);
        }
        return list;
    }
}
