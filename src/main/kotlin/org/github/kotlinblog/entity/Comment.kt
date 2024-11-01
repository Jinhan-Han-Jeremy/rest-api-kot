package org.github.kotlinblog.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Schema(description = "댓글 정보를 담은 데이터 모델") // 클래스 설명 추가
@Entity // 엔티티 클래스 선언
@Table(name = "comment") // "comment" 테이블과 매핑
@EntityListeners(AuditingEntityListener::class) // 자동 생성·수정 시간 관리
data class Comment(

    @Id // 기본 키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
    @Schema(description = "댓글 고유 식별", example = "1", required = true)
    var id: Long? = null, // 고유 식별자 필드

    @Size(min = 1, message = "댓글 내용은 최소 1자 이상 입력해야 합니다.")
    @Schema(description = "댓글 내용", example = "This is a comment", required = true)
    var comment: String, // 댓글 내용 저장

    @ManyToOne(fetch = FetchType.LAZY) // Post와 다대일 관계 (지연 로딩)
    @JoinColumn(name = "post_id", insertable = false, updatable = false) // 외래 키 post_id 매핑
    @JsonBackReference // JSON 직렬화 시 순환 참조 방지
    @Schema(description = "댓글이 참조하는 게시물", required = true)
    var post: Post, // 댓글이 참조하는 Post 객체

    @Schema(description = "댓글 생성 시간", example = "2024-10-30T12:34:56", required = true)
    var createdAt: LocalDateTime = LocalDateTime.now(), // 생성 시간 필드

    @Schema(description = "댓글 수정 시간", example = "2024-10-30T12:34:56", required = true)
    var updatedAt: LocalDateTime = LocalDateTime.now() // 수정 시간 필드
)