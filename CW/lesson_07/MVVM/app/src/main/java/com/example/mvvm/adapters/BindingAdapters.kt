package com.example.mvvm.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean){
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["fromUrl", "progressBar"], requireAll = false)
fun bindFromUrl(view: ImageView, fromUrl: String?, progressBar: ProgressBar?){
    fromUrl?.let {
        Glide.with(view)
            .load(fromUrl)
            .listener(object : RequestListener<Drawable?>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar?.visibility = View.GONE
                    return false
                }
            })
            .into(view)
    }
}