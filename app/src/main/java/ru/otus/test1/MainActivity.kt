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
import ru.otus.test1.entity.ImageData


class MainActivity : AppCompatActivity() {

    private val adapter = ListDelegationAdapter<List<ImageData>>(
        imageDelegate { openDetailActivity(it) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onViewCreated()

        val service = ApiService.makeRetrofitService()
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

    private fun imageDelegate(itemClickListener: (Int) -> Unit) =
        adapterDelegate<ImageData, ImageData>(R.layout.image_item) {
            val image: ImageView = findViewById(R.id.imageView)
            itemView.setOnClickListener { itemClickListener.invoke(item.id) }
            bind { diffPayloads ->
                Glide
                    .with(image.context)
                    .load(item.download_url)
                    .centerCrop()
                    .into(image)
            }
        }

    private fun openDetailActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ARG_ID, id)
        startActivity(intent)
    }
}
