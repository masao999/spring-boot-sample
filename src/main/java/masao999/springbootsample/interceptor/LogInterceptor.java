package masao999.springbootsample.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ログ出力インターセプタ
 */
@Aspect
@Component
public class LogInterceptor {

    /**
     * ロガー
     */
    private final Logger logger = LoggerFactory.getLogger("spring-boot-sample");

    /**
     * 全メソッドの開始時にinfoログ出力
     *
     * @param joinPoint 挿入場所
     */
    @Before("execution(* masao999.springbootsample..*.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        info(joinPoint.getTarget().getClass().toString(),
                joinPoint.getSignature().getName(), "start");
    }

    /**
     * 全メソッドの開始時にinfoログ出力
     *
     * @param joinPoint 挿入場所
     */
    @After("execution(* masao999.springbootsample..*.*(..))")
    public void invokeAfter(JoinPoint joinPoint) {
        info(joinPoint.getTarget().getClass().toString(),
                joinPoint.getSignature().getName(), "end");
    }

    /**
     * infoログ出力
     *
     * @param className  クラス名
     * @param methodName メソッド名
     * @param message    出力内容
     */
    private void info(String className, String methodName, String message) {
        logger.info(className + "." + methodName + "() " + message + ".");
    }
}
