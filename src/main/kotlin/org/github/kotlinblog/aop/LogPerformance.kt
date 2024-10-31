package org.github.kotlinblog.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Retention(AnnotationRetention.RUNTIME)
//이 어노테이션은 런타임까지 유지되며, 런타임 시에 리플렉션을 통해 어노테이션에 접근
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
//대상을 지정합니다. 여기서는 함수(FUNCTION), 프로퍼티의 getter와 setter에 사용
annotation class Performance

@Aspect
//Aspect는 특정 포인트에서 부가적인 작업을 수행하는 코드
@Component
class LogPerformance {

    // PointCut : 적용할 지점 또는 범위 선택
    @Around("@annotation(Performance)")
    @Throws(Throwable::class)
//    @Around("ㅅ")
    private fun publicTarget(pjp: ProceedingJoinPoint): Any {
        //ProceedingJoinPoint은 AOP에서 메소드 호출 시의 상태를 나타내는 객체
        println("성능 측정을 시작합니다.")
        val sw = StopWatch()
        sw.start()

        // 비즈니스 로직 (메인 로직)
        val result = pjp.proceed()
        sw.stop()
        println("성능 측정이 끝났습니다.")
//        println("걸린시간: {} ms", sw.lastTaskTimeMillis)
        return result

    }
}
//AOP를 활용하여 @Performance 어노테이션이 붙은 메소드에 대해 성능 측정