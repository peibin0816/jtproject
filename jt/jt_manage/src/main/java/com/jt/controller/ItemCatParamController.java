package com.jt.controller;

import com.jt.pojo.ItemCatParam;
import com.jt.service.ItemCatParamService;
import com.jt.vo.SysResult;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCatParam")
public class ItemCatParamController {

    @Autowired
    private ItemCatParamService itemCatParamService;

    @GetMapping("/findItemCatParamListByType")
    public SysResult findItemCatParamListByType(ItemCatParam itemCatParam){
        List<ItemCatParam> paramList = itemCatParamService.findItemCatParamListByType(itemCatParam);
        return SysResult.success(paramList);
    }

    @PostMapping("/addItemCatParam")
    public SysResult addItemCatParam(@RequestBody ItemCatParam itemCatParam){
        int res = itemCatParamService.addItemCatParam(itemCatParam);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }


    @PutMapping("/updateItemCatParam")
    public SysResult updateItemCatParam(@RequestBody ItemCatParam itemCatParam){
        int res = itemCatParamService.updateItemCatParam(itemCatParam);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }

    @DeleteMapping("/deleteItemCatParamById")
    public SysResult deleteItemCatParamById(Integer paramId){
        int res = itemCatParamService.deleteItemCatParamById(paramId);
        if(res>0) return SysResult.success();
        else return SysResult.fail();
    }


}
