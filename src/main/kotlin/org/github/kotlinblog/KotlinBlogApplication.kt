package org.github.kotlinblog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class KotlinBlogApplication

fun main(args: Array<String>) {
    runApplication<KotlinBlogApplication>(*args)
}