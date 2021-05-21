package com.jt.service;


import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {

    List<ItemCat> findItemCatList(Integer type);

    int updateStatus(Integer id, boolean status);

    int saveItemCat(ItemCat itemCat);

    int deleteItemCat(ItemCat itemCat);

    int updateItemCat(ItemCat itemCat);
}
