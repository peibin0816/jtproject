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

    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(@PathVariable Integer id,@PathVariable boolean status){
        int res = itemCatService.updateStatus(id,status);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }

    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        int res = itemCatService.saveItemCat(itemCat);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }

    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){
        int res = itemCatService.deleteItemCat(itemCat);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }

    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        int res = itemCatService.updateItemCat(itemCat);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }


}
