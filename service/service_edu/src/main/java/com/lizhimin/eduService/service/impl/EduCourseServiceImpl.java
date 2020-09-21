package com.lizhimin.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizhimin.eduService.entity.CourseInfoForm;
import com.lizhimin.eduService.entity.EduCourse;
import com.lizhimin.eduService.entity.EduCourseDescription;
import com.lizhimin.eduService.mapper.EduCourseMapper;
import com.lizhimin.eduService.query.CourseQuery;
import com.lizhimin.eduService.service.EduCourseDescriptionService;
import com.lizhimin.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizhimin.eduService.util.Course;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-09-21
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        // mybatis学过 动态sql
        String title = courseQuery.getTitle();
        String price = courseQuery.getPrice();
        String status = courseQuery.getStatus();
        String end = courseQuery.getGmtCreate();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            queryWrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(price)) {
            queryWrapper.eq("price",price);
        }
        if(!StringUtils.isEmpty(status)) {
            queryWrapper.ge("status",status);
        }
        if(!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create",end);
        }

        //排序
        queryWrapper.orderByDesc("gmt_create");

        baseMapper.selectPage(pageParam, queryWrapper);
    }



    @Transactional
    @Override
    public Long saveCourseInfo(CourseInfoForm courseInfoForm){

        //保存课程基本信息
        EduCourse course = new EduCourse();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        boolean resultCourseInfo = this.save(course);
        if(!resultCourseInfo){
            System.out.println("课程信息保存失败");
        }

        //保存课程详情信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if(!resultDescription){
            //throw new GuliException(20001, "课程详情信息保存失败");
            System.out.println("课程详情信息保存失败");
        }

        return course.getId();
    }
}
