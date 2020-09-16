package com.lizhimin.eduService.controller;


import com.lizhimin.eduService.entity.EduTeacher;
import com.lizhimin.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
@RestController
@RequestMapping("/eduService/eduteacher")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;
    @GetMapping
    public List<EduTeacher> getEduTeacher(){
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return teacherList;
    }

}

