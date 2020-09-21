package com.lizhimin.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizhimin.eduService.entity.EduSubject;
import com.lizhimin.eduService.mapper.EduSubjectMapper;
import com.lizhimin.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-09-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 根据分类名称查询这个一级分类中否存在
     * @param title
     * @return
     */
    private EduSubject getByTitle(String title) {

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     * @param title
     * @return
     */
    private EduSubject getSubByTitle(String title, String parentId) {

        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(queryWrapper);
    }
}
