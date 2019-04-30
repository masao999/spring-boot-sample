package masao999.springbootsample.interceptor;

import masao999.springbootsample.util.UserInformationCollector;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * ユーザ情報を集めるクラス
     */
    private final UserInformationCollector userInformationCollector;

    /**
     * 開始および終了時のログ出力を行わないクラス名
     * UserInformationCollectorでログ出力を行ってしまうと無限ループになってしまう
     */
    private static final String NO_OUTPUT_LOG_CLASS_NAME = "UserInformationCollector";

    /**
     * コンストラクタ
     *
     * @param userInformationCollector ユーザ情報を集めるクラス
     */
    public LogInterceptor(UserInformationCollector userInformationCollector) {
        this.userInformationCollector = userInformationCollector;
    }

    /**
     * 全メソッドの開始時にinfoログ出力
     *
     * @param joinPoint 挿入場所
     */
    @Before("execution(* masao999.springbootsample..*.*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        if (joinPoint.getTarget().getClass().toString().endsWith(NO_OUTPUT_LOG_CLASS_NAME)) {
            return;
        }
        info(joinPoint.getTarget().getClass().toString(),
                joinPoint.getSignature().getName(),
                Arrays.stream(joinPoint.getArgs())
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining(",")),
                "start");
    }

    /**
     * 全メソッドの開始時にinfoログ出力
     *
     * @param joinPoint 挿入場所
     */
    @After("execution(* masao999.springbootsample..*.*(..))")
    public void invokeAfter(JoinPoint joinPoint) {
        if (joinPoint.getTarget().getClass().toString().endsWith(NO_OUTPUT_LOG_CLASS_NAME)) {
            return;
        }
        info(joinPoint.getTarget().getClass().toString(),
                joinPoint.getSignature().getName(),
                Arrays.stream(joinPoint.getArgs())
                        .filter(Objects::nonNull)
                        .map(Object::toString)
                        .collect(Collectors.joining(",")),
                "end");
    }

    /**
     * infoログ出力
     *
     * @param className  クラス名
     * @param methodName メソッド名
     * @param args       引数
     * @param message    出力内容
     */
    private void info(
            final String className, final String methodName, final String args, final String message) {
        logger.info("[" + userInformationCollector.getUserName() + "][" +
                userInformationCollector.getRemoteAddr() + "] " +
                className + "." + methodName + "(" + args + ") " + message + ".");
    }
}
