package ru.otus.test1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.otus.test1.entity.ImageData
import ru.otus.test1.net.ApiService
import ru.otus.test1.utils.action

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ARG_ID = "arg:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = getImageId()

        val service = ApiService.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val result = service.getImageDetail(id)
            Log.d("", result.body().toString())
            withContext(Dispatchers.Main) {
                result.body()?.let { showData(it) }
            }
        }
    }

    private fun showData(image: ImageData) {
        val addQuote = { input: String -> "\'$input\'" }
        detailView.text = action(image.author, addQuote)
        Glide
            .with(imageView.context)
            .load(image.download_url)
            .into(imageView)
    }

    private fun getImageId() = intent.getIntExtra(ARG_ID, -1)
}
