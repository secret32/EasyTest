package org.mytest.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    private final String bizPkg = "org.mytest.aop.biz";
    private final String biz = "execution(* " + bizPkg + "..*.*(..))";

    private final String odsPkg = "org.mytest.aop.ods";
    private final String ods = "execution(* " + odsPkg + "..*.*(..))";

    private final String basePkg = "org.mytest.aop.base.impl";
    private final String baseAspect = "execution(* " + basePkg + "..*.*(..))";

    @Pointcut(biz)
    private void bizAspect() {
    }

    @Pointcut(ods)
    private void odsAspect() {
    }

    @Pointcut(baseAspect)
    private void baseAspect() {
    }

    @Before("bizAspect()")
    public void beforeBiz() {
        System.out.println("[before biz]");
    }

    @Before("odsAspect()")
    public void beforeOds() {
        System.out.println("[before ods]");
    }

    @After("bizAspect()")
    public void afterBiz() {
        System.out.println("[after biz]");
    }

    @After("odsAspect()")
    public void afterOds() {
        System.out.println("[after ods]");
    }

    @Before("baseAspect()")
    public void beforeBase(JoinPoint joinPoint) {
        String clzName = joinPoint.getTarget().getClass().getName();
        if (clzName.startsWith(bizPkg)) {
            beforeBiz();
        } else if (clzName.startsWith(odsPkg)) {
            beforeOds();
        } else {
            System.out.println("invalid pkg: " + clzName);
        }
    }

    @After("baseAspect()")
    public void afterBase(JoinPoint joinPoint) {
        String clzName = joinPoint.getTarget().getClass().getName();
        if (clzName.startsWith(bizPkg)) {
            afterBiz();
        } else if (clzName.startsWith(odsPkg)) {
            afterOds();
        } else {
            System.out.println("invalid pkg: " + clzName);
        }
    }

}
