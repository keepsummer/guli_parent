package com.lizhimin.eduService.controller;


import com.lizhimin.eduService.entity.EduTeacher;
import com.lizhimin.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduService/eduteacher")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public List<EduTeacher> getEduTeacher(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return teacherList;
    }

    /**
     *逻辑删除讲师
     *
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}") //id 值要通过路径传递
    public boolean delTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){ //@PathVariable 得到路径中的id值。
        return eduTeacherService.removeById(id);
    }

}

