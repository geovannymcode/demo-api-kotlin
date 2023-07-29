package com.guatejug.demo.service

import com.guatejug.demo.model.Speaker
import com.guatejug.demo.repository.SpeakerRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DefaultSpeakerService(private val speakerRepository: SpeakerRepository) : SpeakerService {
    override suspend fun saveSpeaker(speaker: Speaker): Speaker {
        return speakerRepository.save(speaker)
    }

    override suspend fun findAllSpeakers(): Flow<Speaker> {
        return speakerRepository.findAll()
    }

    override suspend fun findSpeakerById(id: Long): Speaker? {
       return speakerRepository.findById(id)
    }

    override suspend fun deleteSpeakerById(id: Long) {
        val foundSpeaker = findSpeakerById(id)
        if(foundSpeaker != null) {
            speakerRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Speaker with id $id not found.")
        }
    }

    override suspend fun updateSpeaker(id: Long, requestedSpeaker: Speaker): Speaker {
        val foundSpeaker = findSpeakerById(id)
        return foundSpeaker?.let {
            speakerRepository.save(requestedSpeaker.copy(id = foundSpeaker.id))
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND,"Speaker with id $id not found.")
    }
}