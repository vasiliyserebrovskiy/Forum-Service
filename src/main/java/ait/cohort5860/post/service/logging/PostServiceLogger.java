package ait.cohort5860.post.service.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author Vasilii Serebrovskii
 * @version 1.0 (02.07.2025)
 */
@Service
@Slf4j(topic = "Post Service")
@Aspect
public class PostServiceLogger {

    @Pointcut("execution(public * ait.cohort5860.post.service.PostServiceImpl.*(Long)) && args(id)")
    public void findById(Long id) {}

    @Pointcut("@annotation(ait.cohort5860.post.service.logging.PostLogger)")
    public void annotatePostLogger(){}

    @Pointcut("execution(public java.util.List<ait.cohort5860.post.dto.PostDto> ait.cohort5860.post.service.PostServiceImpl.findPosts*(..))")
    public void bulkFindPostsLogger() {}


    @Before("findById(id)")
    public void logFindById(Long id) {
        log.info("Find post by id: {}", id);
    }

    @AfterReturning("annotatePostLogger()")
    public void logAnnotatePostLogger(JoinPoint joinPoint) {
        log.info("Annotate by PostLogger method : {}, done", joinPoint.getSignature().getName());
    }

    @Around( "bulkFindPostsLogger()")
    public Object logBulkFindPostsLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String str) {
                args[i] = str.toLowerCase();
            }
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(args);
        long end = System.currentTimeMillis();
        log.info("method: {}, time: {} ms ", joinPoint.getSignature().getName(), end - start);
        return result;
    }
}
