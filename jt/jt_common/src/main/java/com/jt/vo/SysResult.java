package com.jt.vo;

import com.jt.pojo.BasePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysResult extends BasePojo {

    private Integer status;//状态码：成功200，失败201
    private String msg;
    private Object data;

    public static SysResult fail(){
        return new SysResult(201,"服务器运行失败",null);
    }

    public static SysResult success(){
        return new SysResult(200,"服务器运行正常",null);
    }

    public static SysResult success(Object data){
        return new SysResult(200,"服务器运行正常",data);
    }

    public static SysResult success(String msg,Object data){
            return new SysResult(200,msg,null);
    }

}
