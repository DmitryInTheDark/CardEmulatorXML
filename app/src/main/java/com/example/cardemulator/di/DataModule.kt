package com.example.cardemulator.di

import android.content.Context
import androidx.room.Room
import com.example.data.data.local.RoomDB
import com.example.data.data.local.dao.CardsDao
import com.example.data.data.local.dao.UserDao
import com.example.data.data.local.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRoomDB(context: Context): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, RoomDB.DB_NAME)
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(roomDB: RoomDB) = roomDB.userDao

    @Provides
    @Singleton
    fun provideCardsDao(roomDB: RoomDB) = roomDB.cardsDao

    @Provides
    @Singleton
    fun provideUserRepository(context: Context, userDao: UserDao, cardsDao: CardsDao) =
        UserRepository(context, userDao, cardsDao)


}