package org.github.kotlinblog.config

import org.github.kotlinblog.interceptor.HttpInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
//스프링의 설정 클래스임을 나타냅니다. Spring은 이 클래스에서
//정의된 빈(bean)과 설정을 읽고 애플리케이션 컨텍스트에 적용
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        //addInterceptor() 메서드를 통해 HttpInterceptor를 등록
        registry.addInterceptor(HttpInterceptor())
    }
}