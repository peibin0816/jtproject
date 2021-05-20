package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
@CrossOrigin
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    @GetMapping("/findItemCatList/{type}")
    public SysResult findItemCatList(@PathVariable Integer type){
        List<ItemCat> itemCatList = itemCatService.findItemCatList(type);
        return SysResult.success(itemCatList);
    }
}
