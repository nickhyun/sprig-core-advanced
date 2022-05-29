package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6Advice {

    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void before(JoinPoint joinPoint){
        log.info("before {}" , joinPoint.getSignature());
    }

    @AfterReturning(value="hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        log.info("AfterReturning {} {}" , joinPoint.getSignature(), result);
    }
    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        log.info("AfterThrowing {} {}" , joinPoint.getSignature(), ex.getMessage());
    }
    @After("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void after(JoinPoint joinPoint){
        log.info("after {}" , joinPoint.getSignature());
    }



    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("Start Transaction {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("Commit Transaction {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("Rollback Transaction {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("Release Transaction {}", joinPoint.getSignature());
        }
    }

}
