package com.guatejug.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("app.speaker")
data class Speaker(
    @Id val id: Long? = null,
    val name: String,
    val country: String)
