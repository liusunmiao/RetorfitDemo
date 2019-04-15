package com.lsm.retorfitdemo.base.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Presenter注解 用于动态创建多个Presenter
 */
@Target(ElementType.FIELD)//作用在属性上面
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface InjectPresenter {

}
