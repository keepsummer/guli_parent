package com.lizhimin.eduService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName EduTeaacherApplication
 * @Author lizhimin
 * @Date 2020/9/16 17:52
 * @Description EduTeaacherApplication
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lizhimin"}) //设置包扫描
public class EduTeaacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeaacherApplication.class, args);
    }
}
