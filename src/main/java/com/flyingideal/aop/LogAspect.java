package com.flyingideal.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author yanchao
 * @date 2018/1/31 10:28
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.flyingideal.service.BlurredPasswordService.getReletedSubject(..))")
    public void logBeforeMethodInvoke(JoinPoint jp) {
        System.out.println("logBeforeMethodInvoke");
    }
}
