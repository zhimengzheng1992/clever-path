package bcit.cst.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Zhimeng Zheng
 * @version 2025-08-27
 */
@Slf4j
@Aspect
@Component
public class ExecutionTimeAspect {
    // 环绕通知，拦截所有 service 包下的类的方法
    @Around("execution(* bcit.cst.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature() + " 执行耗时: " + (end - start) + "ms");

        return result;
    }
}
