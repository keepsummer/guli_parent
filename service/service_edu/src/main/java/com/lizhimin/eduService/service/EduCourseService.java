package com.lizhimin.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhimin.eduService.entity.CourseInfoForm;
import com.lizhimin.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lizhimin.eduService.entity.EduTeacher;
import com.lizhimin.eduService.query.CourseQuery;
import com.lizhimin.eduService.query.TeacherQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-09-21
 */
public interface EduCourseService extends IService<EduCourse> {
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);
    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    Long saveCourseInfo(CourseInfoForm courseInfoForm);
}
