package ru.otus.test1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.otus.test1.di.DaggerMainComponent
import ru.otus.test1.entity.ImageData
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var service:Api

    @Inject
    lateinit var  adapter : ListDelegationAdapter<List<ImageData>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onViewCreated()
        CoroutineScope(IO).launch {
            val result = service.getImages()
            Log.d("", result.body().toString())
            withContext(Dispatchers.Main) {
                result.body()?.let { showData(it) }
            }
        }
    }

    private fun onViewCreated() {
        val layoutManager = LinearLayoutManager(this)
        listView.layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(
            listView.context,
            layoutManager.orientation
        )
        listView.addItemDecoration(divider)
        listView.adapter = adapter
    }

    private fun showData(images: List<ImageData>) {
        adapter.items = images
        adapter.notifyDataSetChanged()
    }




}
