package org.github.kotlinblog.controller

import org.springframework.web.bind.annotation.*
import org.github.kotlinblog.aop.NativeKotlinPerformence
import org.github.kotlinblog.aop.Performance
import org.github.kotlinblog.entity.Post
import org.github.kotlinblog.service.BlogService

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.util.*

data class CreatePostReqDto (
    val title: String,
    val body: String
)

@RestController
@RequestMapping("/blog")
class BlogController (val blogService: BlogService) {
    @Value("\${test.a:0}")
    private val a: Int = 0

    @Value("\${test.b:''}")
    private val b: String = ""

    //블로그 포스트 읽기 parameter idx
    // request ("page", required = false, defaultValue = 0.toString()) page: Int
    @GetMapping("/{idx}")
    fun readOnePost(@PathVariable("idx") idx:Long, @RequestParam("page", required = false, defaultValue = 0.toString()) page: Int): Post {
        return blogService.readOnePost(idx)
    }

    //블로그 포스트 읽기
    @GetMapping()
    @Performance()
    //performance는 데이터 로그 기록 및 실행 시간 측정
    fun readAllPost(): List<Post> {
        return blogService.readAllPost()
    }

    //블로그 포스트 생성
    @PostMapping()
    @Performance()
    fun createPost(@RequestBody() createPostReqDto: CreatePostReqDto) = NativeKotlinPerformence("test") {
        return@NativeKotlinPerformence blogService.createPost(createPostReqDto.title, createPostReqDto.body)
    }

    //블로그 포스트 수정
    @PutMapping("/{idx}")
    fun modifyPost(@PathVariable("idx") idx:Long, @RequestBody() createPostReqDto: CreatePostReqDto): Boolean {
        return blogService.modifyPost(idx, createPostReqDto.title, createPostReqDto.body)
    }

    //블로그 포스트 삭제
    @DeleteMapping("/{idx}")
    fun deletePost(@PathVariable("idx") idx:Long): Boolean {
        return blogService.deletePost(idx)
    }
}