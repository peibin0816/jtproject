package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            for(ItemCat teoitemCat:twolist){
                QueryWrapper<ItemCat> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("parent_id",teoitemCat.getId());
                List<ItemCat> threelist  = itemCatMapper.selectList(queryWrapper3);
                teoitemCat.setChildren(threelist);
            }
            itemCat.setChildren(twolist);
        }
        return list;
    }

    @Override
    public int updateStatus(Integer id, boolean status) {
        return itemCatMapper.updateById(new ItemCat().setId(id).setStatus(!status));
    }

    @Override
    @Transactional
    public int saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        int insert = itemCatMapper.insert(itemCat);
        return insert;
    }

    @Override
    public int deleteItemCat(ItemCat itemCat) {
        int res = 0;
        if(itemCat.getLevel()==3) res = itemCatMapper.deleteById(itemCat.getId());
        else if(itemCat.getLevel()==2){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("parent_id",itemCat.getId());
            itemCatMapper.delete(queryWrapper);
            res = itemCatMapper.deleteById(itemCat.getId());
        }else if(itemCat.getLevel()==1){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("parent_id",itemCat.getId());
            List<ItemCat> list = itemCatMapper.selectList(queryWrapper);
            for(ItemCat ic :list){
                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("parent_id",ic.getId());
                itemCatMapper.delete(queryWrapper2);
                itemCatMapper.deleteById(ic.getId());
            }
            res = itemCatMapper.deleteById(itemCat.getId());
        }
        return res;
    }

    @Override
    public int updateItemCat(ItemCat itemCat) {
        return itemCatMapper.updateById(itemCat);
    }
}
