package com.napptilus.oompaloompa.Utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideCustom {
    fun setImageInView(imageview: ImageView, url: String, context: Context) {
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .into(imageview);
    }
}