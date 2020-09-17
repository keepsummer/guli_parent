package com.lizhimin.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhimin.commonutils.R;
import com.lizhimin.eduService.entity.EduTeacher;
import com.lizhimin.eduService.query.TeacherQuery;
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
    public R getEduTeacher(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.ok().data("items",teacherList);
    }

    /**
     *逻辑删除讲师
     *
     * @return
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}") //id 值要通过路径传递
    public R delTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){ //@PathVariable 得到路径中的id值。
        boolean b = eduTeacherService.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }

    }

//    @ApiOperation(value = "分页讲师列表")
//    @GetMapping("{page}/{limit}")
//    public R pageList(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit){
//
//        Page<EduTeacher> pageParam = new Page<>(page, limit);
//
//        eduTeacherService.page(pageParam, null);
//        List<EduTeacher> records = pageParam.getRecords();
//        long total = pageParam.getTotal();
//
//        return  R.ok().data("total", total).data("rows", records);
//    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    TeacherQuery teacherQuery){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        eduTeacherService.save(teacher);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return R.ok();
    }

}

