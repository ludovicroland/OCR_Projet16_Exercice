package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.ExerciseDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int
) {
    fun toDto(): ExerciseDto {
        return ExerciseDto(
            startTime = this.startTime.toEpochSecond(ZoneOffset.UTC), // Convert to timestamp
            duration = this.duration,
            category = this.category.name, // Convert Enum to String
            intensity = this.intensity
        )
    }

    companion object {
        fun fromDto(dto: ExerciseDto): Exercise {
            return Exercise(
                id = dto.id,
                startTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dto.startTime),
                    ZoneId.systemDefault()
                ),
                duration = dto.duration,
                category = ExerciseCategory.valueOf(dto.category),
                intensity = dto.intensity
            )
        }
    }
}
