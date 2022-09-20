package com.napptilus.oompaloompa.dagger

import android.content.Context
import androidx.room.Room
import com.napptilus.oompaloompa.database.WorkersDatabase
import com.napptilus.oompaloompa.database.WorkersOompaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): WorkersDatabase {
        return Room.databaseBuilder(
            appContext,
            WorkersDatabase::class.java,
            "Workers"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(usersDatabase: WorkersDatabase): WorkersOompaDao {
        return usersDatabase.workerDao
    }

}