package com.lizhimin.eduService.controller;


import com.lizhimin.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eduService/user")
@CrossOrigin
public class EduLoginController {
    /**
     * 登录方法
     * @return token
     */
    @PostMapping("login")
    public R Login(){
        return R.ok().data("token","admin");
    }

    /**
     * 用户信息
     * @return 用户信息
     */
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}

