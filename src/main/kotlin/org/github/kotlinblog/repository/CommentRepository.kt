package org.github.kotlinblog.repository

import org.github.kotlinblog.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface CommentRepository : JpaRepository<Comment, Long> {}