package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow


class ReadAppEntry(private val localUserManager: LocalUserManager) {

    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}