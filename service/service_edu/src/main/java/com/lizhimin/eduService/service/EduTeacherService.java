package com.lizhimin.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhimin.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhimin.eduService.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-09-16
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
