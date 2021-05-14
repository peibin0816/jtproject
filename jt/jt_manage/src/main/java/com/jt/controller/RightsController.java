package com.jt.controller;

import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {

    @Autowired
    private RightsService rightsService;

    @GetMapping("/getRightsList")
    public SysResult getRightsList(){
        List<Rights> rights = rightsService.findall();
        System.out.println(rights);
        if(rights!=null) return SysResult.success("服务器调用成功",rights);
        else return SysResult.fail();
    }

}
