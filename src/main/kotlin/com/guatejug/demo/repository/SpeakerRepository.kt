package com.guatejug.demo.repository

import com.guatejug.demo.model.Speaker
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface SpeakerRepository : CoroutineCrudRepository<Speaker, Long> {
}