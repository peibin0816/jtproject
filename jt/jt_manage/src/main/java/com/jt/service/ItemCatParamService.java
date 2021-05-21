package com.jt.service;

import com.jt.pojo.ItemCatParam;

import java.util.List;

public interface ItemCatParamService {
    List<ItemCatParam> findItemCatParamListByType(ItemCatParam itemCatParam);

    int addItemCatParam(ItemCatParam itemCatParam);

    int updateItemCatParam(ItemCatParam itemCatParam);

    int deleteItemCatParamById(Integer paramId);
}
