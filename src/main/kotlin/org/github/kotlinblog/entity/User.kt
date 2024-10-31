package org.github.kotlinblog.entity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import jakarta.validation.constraints.Email

// 사용자 역할을 정의하는 열거형
enum class UserRole {
    ADMIN, USER // 관리자와 일반 사용자 역할 구분
}

@Schema(description = "사용자 정보를 담은 데이터 모델") // 클래스 설명 추가
@Entity // 이 클래스를 JPA 엔티티로 지정
@Table(name = "users") // "users" 테이블과 매핑
class User (
    @Id // 기본 키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 자동 증가
    @Schema(description = "사용자 고유 식별자", example = "1", required = true)
    var id: Long? = null, // 고유 식별자 필드

    @Email(message = "이메일 형식이 아닙니다") // 이메일 형식 유효성 검사
    @Schema(description = "사용자 이메일", example = "user@example.com", required = true)
    var email: String, // 사용자 이메일 필드

    @Schema(description = "사용자 비밀번호", example = "password123", required = true)
    var password: String, // 사용자 비밀번호 필드

    @Enumerated(EnumType.STRING) // 열거형을 문자열로 저장
    @ElementCollection(fetch = FetchType.LAZY) // 역할 정보 지연 로딩
    @Schema(description = "사용자 역할 목록 (ADMIN 또는 USER)")
    var roles: MutableSet<UserRole>? = null, // 사용자 역할 정보 저장 (Set 형태)
)