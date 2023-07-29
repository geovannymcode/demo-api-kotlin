package com.guatejug.demo.service

import com.guatejug.demo.model.Speaker
import kotlinx.coroutines.flow.Flow

interface SpeakerService {
    suspend fun saveSpeaker(speaker: Speaker): Speaker

    suspend fun findAllSpeakers(): Flow<Speaker>

    suspend fun findSpeakerById(id: Long): Speaker?

    suspend fun deleteSpeakerById(id: Long)

    suspend fun updateSpeaker(id: Long, requestedSpeaker:Speaker): Speaker
}