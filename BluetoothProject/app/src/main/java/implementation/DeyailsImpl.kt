package implementation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import entity.DataEntity
import entity.DetailsDao

@Database(entities = [DataEntity::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DetailsDao

    companion object {
        @Volatile
        private var INSTANCE: DataDatabase? = null

        fun getDatabase(context: Context): DataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataDatabase::class.java,
                    "data_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
