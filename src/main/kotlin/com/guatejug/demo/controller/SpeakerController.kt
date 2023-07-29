package com.guatejug.demo.controller

import com.guatejug.demo.dto.SpeakerRequest
import com.guatejug.demo.dto.SpeakerResponse
import com.guatejug.demo.model.Speaker
import com.guatejug.demo.service.SpeakerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PutMapping

@RestController
@RequestMapping("/api/speakers")
class SpeakerController(private val speakerService: SpeakerService) {
    @GetMapping
    suspend fun findSpeaker(): ResponseEntity<Flow<SpeakerResponse>>
    {
        val speakers = speakerService.findAllSpeakers()
        val speakerResponse = speakers.map {speaker -> speaker.toResponse()}
        return ResponseEntity.ok(speakerResponse)
    }

    @GetMapping("/{id}")
    suspend fun findSpeakerById(@PathVariable id: Long): ResponseEntity<SpeakerResponse> {
        val speaker = speakerService.findSpeakerById(id)
        return speaker?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()
    }



    @PostMapping
    suspend fun createSpeaker(@RequestBody speakerRequest: SpeakerRequest): ResponseEntity<SpeakerResponse> {
        val newSpeaker = speakerService.saveSpeaker(speakerRequest.toModel())
        return ResponseEntity.ok(newSpeaker.toResponse())

    }

    @DeleteMapping("/{id}")
    suspend fun deleteSpeakerById(@PathVariable id: Long) {
        speakerService.deleteSpeakerById(id)
    }



    @PutMapping("/{id}")
    suspend fun updateSpeaker(@PathVariable id: Long, @RequestBody speakerRequest: SpeakerRequest): ResponseEntity<SpeakerResponse> {
        val updatedSpeaker = speakerService.updateSpeaker(id, speakerRequest.toModel())
        return ResponseEntity.ok(updatedSpeaker.toResponse())
    }

    private fun Speaker.toResponse(): SpeakerResponse =
        SpeakerResponse(
            id = this.id!!,
            name = this.name,
            country = this.country
        )
    private fun SpeakerRequest.toModel(): Speaker =
        Speaker(
            name = this.name,
            country = this.country
        )

}