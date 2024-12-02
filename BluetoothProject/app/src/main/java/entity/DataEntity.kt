package entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detials_table")
data class DataEntity(
    @PrimaryKey val timestamp: Long,
    val details: String
)
