package masao999.springbootsample.interceptor;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * {@link LogInterceptor}のテストケース
 */
public class LogInterceptorTest {

    /**
     * ログ出力インターセプタ
     */
    private LogInterceptor logInterceptor;

    @Mock
    private JoinPoint mockJoinPoint;

    @Mock
    private Signature mockSignature;

    @Mock
    private Appender mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    /**
     * 事前処理
     */
    @Before
    @SuppressWarnings(value = {"unchecked"})
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        logInterceptor = new LogInterceptor();
        final Logger logger = (Logger) LoggerFactory.getLogger("spring-boot-sample");
        logger.addAppender(mockAppender);
    }

    /**
     * 事後処理
     */
    @After
    @SuppressWarnings(value = {"unchecked"})
    public void teardown() {
        final Logger logger = (Logger) LoggerFactory.getLogger("spring-boot-sample");
        logger.detachAppender(mockAppender);
    }

    /**
     * {@link LogInterceptor#invokeBefore(JoinPoint)}のテストケース
     */
    @Test
    @SuppressWarnings(value = {"unchecked"})
    public void testInvokeBefore() {
        when(mockJoinPoint.getTarget()).thenReturn(new Object());
        when(mockJoinPoint.getSignature()).thenReturn(mockSignature);
        String[] args = {"aaa", "bbb"};
        when(mockJoinPoint.getArgs()).thenReturn(args);

        logInterceptor.invokeBefore(mockJoinPoint);

        verify(mockJoinPoint, times(1)).getTarget();
        verify(mockJoinPoint, times(1)).getSignature();
        verify(mockAppender).doAppend(captorLoggingEvent.capture());

        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertThat(loggingEvent.getLevel(), is(Level.INFO));
        assertThat(loggingEvent.getMessage(), is("class java.lang.Object.null(aaa,bbb) start."));
    }

    /**
     * {@link LogInterceptor#invokeAfter(JoinPoint)}のテストケース
     */
    @Test
    @SuppressWarnings(value = {"unchecked"})
    public void testInvokeAfter() {
        when(mockJoinPoint.getTarget()).thenReturn(new Object());
        when(mockJoinPoint.getSignature()).thenReturn(mockSignature);
        String[] args = {"aaa", "bbb"};
        when(mockJoinPoint.getArgs()).thenReturn(args);

        logInterceptor.invokeAfter(mockJoinPoint);

        verify(mockJoinPoint, times(1)).getTarget();
        verify(mockJoinPoint, times(1)).getSignature();
        verify(mockAppender).doAppend(captorLoggingEvent.capture());

        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        assertThat(loggingEvent.getLevel(), is(Level.INFO));
        assertThat(loggingEvent.getMessage(), is("class java.lang.Object.null(aaa,bbb) end."));
    }
}
