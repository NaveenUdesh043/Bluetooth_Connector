package viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sachini.bluetoothproject.R
import entity.DataEntity

class DataAdapter(private val dataList: List<DataEntity>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timestamp: TextView = view.findViewById(R.id.textTimestamp)
        val details: TextView = view.findViewById(R.id.textDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = dataList[position]
        holder.timestamp.text = data.timestamp.toString()
        holder.details.text = data.details
    }

    override fun getItemCount(): Int = dataList.size
}
