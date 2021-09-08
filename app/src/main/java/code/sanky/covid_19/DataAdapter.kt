package code.sanky.covid_19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(val regional: List<Regional>) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var stateName : TextView = itemView.findViewById(R.id.stateTv)
        var confirmed : TextView = itemView.findViewById(R.id.confirmedTv)
        var active : TextView = itemView.findViewById(R.id.activeTv)
        var recovered :TextView = itemView.findViewById(R.id.recoveredTv)
        var deceased : TextView = itemView.findViewById(R.id.deceasedTv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_list, parent , false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.stateName.text = regional[position].loc
        holder.confirmed.text = regional[position].totalConfirmed.toString()
        holder.active.text = (regional[position].totalConfirmed - (regional[position].deaths + regional[position].discharged)).toString()
        holder.recovered.text = regional[position].discharged.toString()
        holder.deceased.text = regional[position].deaths.toString()
    }

    override fun getItemCount(): Int {
        return regional.size
    }
}