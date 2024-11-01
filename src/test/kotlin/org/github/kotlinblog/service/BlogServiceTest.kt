package org.github.kotlinblog.service

import org.github.kotlinblog.entity.Post
import org.github.kotlinblog.exception.BaseException
import org.github.kotlinblog.exception.BaseResponseCode
import org.github.kotlinblog.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class BlogService (val postRepository: PostRepository){

    fun readOnePost(id: Long): Post {
        return postRepository.findById(id).orElse(null) ?: throw BaseException(BaseResponseCode.POST_NOT_FOUND)
    }

    fun readAllPost(): List<Post> {
        val posts = postRepository.findAll()
        return posts
    }

    fun createPost(title: String, body: String): Boolean {
        val post = Post(title = title, body = body)
        postRepository.save(post)
        return true
    }

    fun modifyPost(id: Long, title: String, body: String): Boolean {
        val post = postRepository.findById(id).orElse(null) ?: throw BaseException(BaseResponseCode.POST_NOT_FOUND)

        post.title = title
        post.body = body

        return true
    }

    fun deletePost(id: Long): Boolean {
        postRepository.deleteById(id)
        return true
    }
}