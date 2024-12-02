package viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import entity.DataEntity
import entity.DetailsDao
import implementation.DataDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dao: DetailsDao = DataDatabase.getDatabase(application).dataDao()

    fun getAllData(): LiveData<List<DataEntity>> = dao.getAllData()

    fun insertData(data: DataEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(data)
        }
    }
}
