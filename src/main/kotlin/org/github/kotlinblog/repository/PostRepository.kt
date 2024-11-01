package org.github.kotlinblog.repository
import org.github.kotlinblog.entity.Post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    @Query("SELECT distinct p FROM Post p JOIN FETCH p.comments")
    fun findAllWithCommentsUsingFetchJoin(): List<Post>
}