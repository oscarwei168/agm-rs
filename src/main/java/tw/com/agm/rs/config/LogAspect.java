package tw.com.agm.rs.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class LogAspect {

    private static final String LOG_BEGIN_TEMPLATE = "[Enter] {}.{}";
    private static final String LOG_END_TEMPLATE = "[Exit] {}.{}";

    @Pointcut("execution(* tw.com.agm.rs.action..*(..))")
    public void pointcut() {
    }

    @Around("@annotation(tw.com.agm.rs.config.LogExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
	MethodSignature methodSignature = (MethodSignature) point.getSignature();
	String className = methodSignature.getDeclaringType().getSimpleName();
	String methodName = methodSignature.getName();
	StopWatch stopWatch = new StopWatch(className + "." + methodName);
	stopWatch.start(methodName);
	Object result = point.proceed();
	stopWatch.stop();
	log.info("execution: {}", stopWatch.prettyPrint());
	return result;
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
