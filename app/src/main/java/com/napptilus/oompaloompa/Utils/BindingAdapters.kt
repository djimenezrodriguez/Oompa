package com.napptilus.oompaloompa.Utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url).into(view)
    }
}

@BindingAdapter("textCheckHtml")
fun textCheckHtml(textView: TextView, textToPut: String?) {
    if (!textToPut.isNullOrEmpty()) {
        textView.text = Jsoup.parse(textToPut).text()
    }

}