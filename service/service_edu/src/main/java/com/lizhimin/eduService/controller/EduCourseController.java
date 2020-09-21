package com.lizhimin.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhimin.commonutils.R;
import com.lizhimin.eduService.entity.EduCourse;
import com.lizhimin.eduService.query.CourseQuery;
import com.lizhimin.eduService.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-21
 */
@CrossOrigin
@RestController
@RequestMapping("/eduService/educourse")
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;


    @ApiOperation(value = "所有课程列表")
    @GetMapping("findAll")
    public R getEduCourse(){
        List<EduCourse> eduCourseList = eduCourseService.list(null);
        return R.ok().data("eduCourseList",eduCourseList);
    }

    /**
     *逻辑删除课程
     *
     * @return
     */
    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}") //id 值要通过路径传递
    public R delTeacherById(@ApiParam(name = "id", value = "课程ID", required = true)
                            @PathVariable Long id){ //@PathVariable 得到路径中的id值。
        boolean b = eduCourseService.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }

    }


    @ApiOperation(value = "新增课程")
    @PostMapping("addCourse")
    public R save(
            @ApiParam(name = "course", value = "课程对象", required = true)
            @RequestBody EduCourse eduCourse){

        eduCourseService.save(eduCourse);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable Long id){

        EduCourse eduCourse = eduCourseService.getById(id);
        return R.ok().data("course", eduCourse);
    }
    @ApiOperation(value = "根据ID修改课程")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable Long id,

            @ApiParam(name = "course", value = "讲师对象", required = true)
            @RequestBody EduCourse eduCourse){

        eduCourse.setId(Long.valueOf(id));
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) CourseQuery courseQuery) {
        //创建page对象
        Page<EduCourse> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String title = courseQuery.getTitle();
        String price = courseQuery.getPrice();
        String status = courseQuery.getStatus();
        String end = courseQuery.getGmtCreate();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(price)) {
            wrapper.eq("price",price);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.ge("status",status);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        eduCourseService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduCourse> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


}

