package ru.otus.test1.delegates

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import ru.otus.test1.DetailActivity
import ru.otus.test1.R
import ru.otus.test1.entity.ImageData

fun imageDelegate(itemClickListener: (Int) -> Unit) =
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

fun openDetailActivity(ctx: Context, id: Int) {
    val intent = Intent(ctx, DetailActivity::class.java)
    intent.putExtra(DetailActivity.ARG_ID, id)
    ctx.startActivity(intent)
}