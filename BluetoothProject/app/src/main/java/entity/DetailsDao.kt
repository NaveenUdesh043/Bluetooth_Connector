package entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DetailsDao {
    @Insert
    suspend fun insert(data: DataEntity)

    @Query("SELECT * FROM detials_table ORDER BY timestamp DESC")
    fun getAllData(): LiveData<List<DataEntity>>
}
