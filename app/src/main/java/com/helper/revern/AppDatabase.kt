package com.helper.revern

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.helper.revern.tasks.dao.TasksDao
import com.helper.revern.tasks.models.Task

@Database(entities = [(Task::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private lateinit var sInstance: AppDatabase

        fun initialize(context: Context) {
            sInstance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "app-database")
                    .allowMainThreadQueries()
                    .build()
        }

        fun instance() = sInstance
    }

    abstract fun taskDao(): TasksDao

}