package org.github.kotlinblog.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//전역 예외 처리를 위한 어드바이스 클래스를 정의
@RestControllerAdvice
class BaseFilter {
    //BaseException**이라는 커스텀 예외를 처리하기 위한 핸들러
    @ExceptionHandler(*[BaseException::class])
    fun handle(ex: BaseException): ResponseEntity<BaseResponseError> {
        val errorResponse = BaseResponseError(ex.baseResponseCode, ex.baseResponseCode.message)
        return ResponseEntity.status(ex.baseResponseCode.status).body(errorResponse)
    }

    //이 메서드는 NullPointerException, ClassCastException, 그리고 그 외의 일반적인 Exception 타입의 예외를 처리
    @ExceptionHandler(*[java.lang.NullPointerException::class, ClassCastException::class, java.lang.Exception::class])
    fun handle(ex: Exception): ResponseEntity<String> {
        println("======")
        println(ex)
        println(ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("요청 데이터가 올바르지 않습니다")
    }
}