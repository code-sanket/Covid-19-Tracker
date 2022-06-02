package code.sanky.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import code.sanky.covid_19.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var dataAdapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchResult()
        binding.swipe.setOnRefreshListener {
            fetchResult()
        }
    }

    private fun fetchResult() {
       GlobalScope.launch {
           val response = withContext(Dispatchers.IO){RetrofitHelper.api.getData()}
           if (response.isSuccessful){
               binding.swipe.isRefreshing = false
               val apiData = response.body()
               launch(Dispatchers.Main) {
                   bindData(apiData)
               }

           }

       }
    }

    private fun bindData(apiData: UpdatedDataApi?) {

        binding.confirmedTv.text = apiData!!.data.summary.total.toString()
        val active = apiData!!.data.summary.total - (apiData!!.data.summary.deaths + apiData!!.data.summary.discharged)
        binding.activeTv.text = active.toString()
        binding.recoveredTv.text = apiData!!.data.summary.discharged.toString()
        binding.deceasedTv.text = apiData!!.data.summary.deaths.toString()
        val lastUpdatedTime  = "Last Updated 2022-06-13 08:12:47"
        binding.tvLastUpdated.text = lastUpdatedTime
        setRecyclerView(apiData)
    }

    private fun setRecyclerView(apiData: UpdatedDataApi?) {
        dataAdapter = DataAdapter(apiData!!.data.regional)
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.dataRecyclerView.adapter = dataAdapter
    }

}