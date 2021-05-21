package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){
        pageResult = itemService.findByQuery(pageResult);
        return SysResult.success(pageResult);
    }

    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item){
        Integer res = itemService.updateStatus(item);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }

    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO){
        itemService.saveitem(itemVO);
        return SysResult.success();
    }
}
