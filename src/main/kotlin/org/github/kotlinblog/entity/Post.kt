package org.github.kotlinblog.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "게시물 정보를 담고 있는 데이터 모델")
@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener::class)
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "게시물 고유 식별자", example = "1", required = true)
    var id: Long? = null,

    @Size(min = 1, message = "제목은 최소 1자 이상 입력해야 합니다.")
    @Schema(description = "게시물 제목", example = "This is a post title", required = true)
    var title: String,

    @Schema(description = "게시물 내용", example = "This is the body of the post", required = true)
    var body: String,

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonBackReference // JSON 직렬화 시 순환 참조 방지
    @Schema(description = "게시물에 달린 댓글 목록")
    var comments: MutableList<Comment> = mutableListOf(),

    @Schema(description = "게시물 생성 시간", example = "2024-10-30T12:34:56", required = true)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Schema(description = "게시물 수정 시간", example = "2024-10-30T12:34:56", required = true)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)