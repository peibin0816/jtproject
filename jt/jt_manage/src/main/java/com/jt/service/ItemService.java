package com.jt.service;
import com.jt.pojo.Item;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;


public interface ItemService {

    PageResult findByQuery(PageResult pageResult);

    Integer updateStatus(Item item);

    void saveitem(ItemVO itemVO);
}
