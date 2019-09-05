package com.syntun.controller.systemlog;

import java.lang.annotation.*;

/**
 * 名  称：SysLogger
 * 描  述：系统日志注释
 */
@Retention(RetentionPolicy.RUNTIME)//注解会在class中存在，运行时可通过反射获取
@Target(ElementType.METHOD)//目标是方法
@Documented//文档生成时，该注解将被包含在javadoc中，可去掉
public @interface SysLogger {
    /**
     * 模块名称
     */
    String modelName() default "";

    /**
     * 方法类型
     */
    String methodType();
}
