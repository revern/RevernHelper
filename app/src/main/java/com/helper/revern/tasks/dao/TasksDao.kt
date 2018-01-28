package com.helper.revern.tasks.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.helper.revern.tasks.models.Task

@Dao
interface  TasksDao {

    @Query("select * from tasks")
    fun getAllTasks(): List<Task>

    @Query("select * from tasks where type = :type")
    fun getTasksByType(type: String): List<Task>

    @Query("select * from tasks where type = :type order by position ASC")
    fun getAllTasksByTypeSorted(type: String) : List<Task>

    @Query("select * from tasks where id = :id")
    fun getTaskById(id: Int): Task

    @Insert(onConflict = REPLACE)
    fun insertTask(task: Task)

    @Insert(onConflict = REPLACE)
    fun insertAll(tasks: List<Task>)

    @Update(onConflict = REPLACE)
    fun updateTask(task: Task)

    @Update(onConflict = REPLACE)
    fun updateAllTask(tasks: List<Task>)

    @Delete
    fun delete(task: Task)

}