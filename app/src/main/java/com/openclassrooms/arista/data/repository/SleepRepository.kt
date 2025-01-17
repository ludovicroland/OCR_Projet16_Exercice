package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep

class SleepRepository(private val sleepDao: SleepDtoDao) {

    // Get all sleep records
    suspend fun getAllSleeps(): List<Sleep> {
        return sleepDao.getAllSleeps().map { Sleep.fromDto(it) }
    }
}
