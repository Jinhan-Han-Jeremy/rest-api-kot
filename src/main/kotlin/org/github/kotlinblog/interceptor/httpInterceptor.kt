package org.github.kotlinblog.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
//인터셉터 기능은 컨트롤러 호출 전/후/완료 시점에 특정 코드를 수행하는 역할

//인터셉터는 HandlerInterceptor를 상속받아 preHandle, postHandler, afterCompletion 핸들러를
// 상속받아 컨트롤러 호출 전/후 특정 코드를 수행

@Component
//Spring의 컴포넌트 스캔을 통해 해당 클래스를 빈으로 등록
//이 어노테이션이 있는 클래스는 자동으로 스프링 컨테이너에 의해 관리되며, 다른 스프링 빈에 의존성 주입
class HttpInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("[preHandle]")
        //컨트롤러가 실행되기 전에 호출
        //인증 및 인가 처리, 요청 로깅 등의 작업에 사용
        return true
    }

    @Throws(Exception::class)
    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info("[postHandle]")
        //컨트롤러가 요청을 처리한 후에 호출되지만, 뷰가 렌더링되기 전에 호출
        //주로 컨트롤러가 반환한 데이터를 가공하거나, 응답 데이터를 추가할 때 사용
    }

    @Throws(Exception::class)
    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        `object`: Any,
        ex: Exception?
    ) {
        logger.info("[afterCompletion]")
        //뷰가 완전히 렌더링된 후에 호출
        //주로 리소스 정리 (예: 파일 핸들링, 트랜잭션 종료,) 또는 성공/실패 로깅에 사용
    }

}