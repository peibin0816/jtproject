package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.mapper.ItemCatParamMapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.ItemParam;
import com.jt.vo.ItemParamVO;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;
    @Autowired
    private ItemParamMapper itemParamMapper;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Override
    public PageResult findByQuery(PageResult pageResult) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        boolean b = StringUtils.hasLength(pageResult.getQuery());
        queryWrapper.like(b,"title",pageResult.getQuery());
        IPage page = new Page(pageResult.getPageNum(),pageResult.getPageSize());
        page = itemMapper.selectPage(page, queryWrapper);
        List<Item> records = page.getRecords();
        return pageResult.setTotal(page.getTotal()).setRows(records);
    }

    @Override
    @Transactional
    public Integer updateStatus(Item item) {
        return itemMapper.updateById(item.setStatus(!item.getStatus()));
    }

    @Override
    @Transactional
    public void saveitem(ItemVO itemVO) {
        //商品表
        Item item = itemVO.getItem();
        item.setStatus(true);
        itemMapper.insert(item);

        //商品详情
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);

        //商品参数
        ItemParam itemParam = itemVO.getItemParam();
        ItemParamVO[] dynamicArray = itemParam.getDynamicArray();
        ItemParamVO[] staticArray = itemParam.getStaticArray();

        try {
            String dynamicJSON = MAPPER.writeValueAsString(dynamicArray);
            String staticJSON = MAPPER.writeValueAsString(staticArray);
            itemParam.setDynamicArgs(dynamicJSON).setDynamicArgs(staticJSON).setId(item.getId());
            itemParamMapper.insert(itemParam);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
