package com.lizhimin.eduService.controller;


import com.lizhimin.commonutils.R;
import com.lizhimin.eduService.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-21
 */
@Api(description="课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduService/edusubject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R addUser(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        //List<String> msg = subjectService.batchImport(file);
//        if(msg.size() == 0){
//            return R.ok().message("批量导入成功");
//        }else{
//            return R.error().message("部分数据导入失败").data("messageList", msg);
//        }
        return R.ok().message("批量导入成功");
    }

}

