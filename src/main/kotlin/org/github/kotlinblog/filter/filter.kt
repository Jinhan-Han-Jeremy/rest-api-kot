package org.github.kotlinblog.filter

import jakarta.servlet.*
import org.springframework.stereotype.Component
import java.io.IOException

@Component
//Spring의 컴포넌트 스캔을 통해 해당 클래스를 빈으로 등록
//이 어노테이션이 있는 클래스는 자동으로 스프링 컨테이너에 의해 관리되며, 다른 스프링 빈에 의존성 주입
class MyFilter : Filter {
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig?) {
        println("init filter")
        //필터가 처음 생성될 때 한 번만 호출
        //주로 필터에서 필요한 초기 설정을 이곳에서 처리
    }

    //필터의 핵심 로직이 실행되는 메서드
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        println("before filter")
        chain.doFilter(request, response)
        //다음 필터 또는 서블릿으로 요청 전달
        println("after filter")
    }

    @Throws(IOException::class, ServletException::class)
    override fun destroy() {
        //필터가 제거될 때 호출되는 메서드
        println("destroy filter")
    }
}