package com.axelessono.localjsondata.activity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.axelessono.localjsondata.R
import com.axelessono.localjsondata.model.TVShow
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageShow: ImageView
    private lateinit var comeBackBtn: AppCompatButton
    private var tvShow: TVShow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        imageShow = findViewById(R.id.imageShow)
        comeBackBtn = findViewById(R.id.comeBack)

        tvShow = intent.getParcelableExtra("TVSHOW") // On peut enlever <TVShow>
        //.getParcelableExtra<TVShow>("TVSHOW")

        Glide.with(applicationContext)
            .load(tvShow?.imageThumbnailPath)
            .addListener(object : RequestListener<Drawable> {
                @SuppressLint("CheckResult")
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(applicationContext, "Failed to Load image", Toast.LENGTH_SHORT)
                        .show()
                    return true
                }

                @SuppressLint("CheckResult")
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(applicationContext, "${tvShow?.name} is loaded", Toast.LENGTH_SHORT).show()
                    return false
                }

            })
            .into(imageShow)

        /**
         *
         * On Pouvait aussi ecrire ça :
         *
         * Glide.with(applicationContext)
         *   .load(tvShow?.imageThumbnailPath)
         *   .into(imageShow)
         *
         *   C'est juste pour vour montrer la puissance de
         *   GLIDE
         *
         */


        imageShow.setOnClickListener(this)
        comeBackBtn.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.comeBack -> onBackPressed() // Retour à MainActivity
            R.id.imageShow -> Toast.makeText(
                applicationContext,
                "${tvShow?.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}