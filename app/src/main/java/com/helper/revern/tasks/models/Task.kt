package com.helper.revern.tasks.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
        @ColumnInfo(name = "type") var type: String,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "crossed") var crossed: Boolean = false) {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Int = 0

}