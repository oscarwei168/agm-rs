package tw.com.agm.rs.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final String LOG_BEGIN_TEMPLATE = "[Enter] {}.{}";
    private static final String LOG_END_TEMPLATE = "[Exit] {}.{}";

    @Pointcut("execution(* tw.com.agm.rs..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
	Signature signature = joinPoint.getSignature();
	String declaringTypeName = signature.getDeclaringTypeName();
	String methodName = signature.getName();
	log.info(LOG_BEGIN_TEMPLATE, declaringTypeName, methodName);
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
	Signature signature = joinPoint.getSignature();
	String declaringTypeName = signature.getDeclaringTypeName();
	String methodName = signature.getName();
	log.info(LOG_END_TEMPLATE, declaringTypeName, methodName);
    }
}
