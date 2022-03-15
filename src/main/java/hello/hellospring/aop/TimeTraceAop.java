package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect

public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))")  // AOP를 어디에 적용할지 설정.. hellospring.service라 적으면 service만 시간 측정

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = start - finish;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
