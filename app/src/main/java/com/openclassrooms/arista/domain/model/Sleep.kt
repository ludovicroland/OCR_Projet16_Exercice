package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class Sleep(@JvmField var startTime: LocalDateTime, var duration: Int, var quality: Int) {
    companion object {
        fun fromDto(dto: SleepDto): Sleep {
            return Sleep(
                LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dto.startTime),
                    ZoneId.systemDefault()
                ),
                dto.duration,
                dto.quality
            )
        }
    }
}