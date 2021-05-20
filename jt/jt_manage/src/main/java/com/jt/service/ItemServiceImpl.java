package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper itemMapper;
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
    public Integer updateStatus(Item item) {
        return itemMapper.updateById(item.setStatus(!item.getStatus()));
    }
}
