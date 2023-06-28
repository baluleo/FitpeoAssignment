package com.image.fitpeo.assignment.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.image.fitpeo.assignment.R
import com.image.fitpeo.assignment.databinding.ImageDetailActivityBinding
import com.image.fitpeo.assignment.model.ImageDetailModel
import com.squareup.picasso.Picasso

class ImageDetailActivity : AppCompatActivity() {

    private lateinit var binding: ImageDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ImageDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val imageDetailIntent = intent
        val titleText = imageDetailIntent.getStringExtra(MainActivity.TITLE)
        val imageURL = imageDetailIntent.getStringExtra(MainActivity.IMAGE_URL)

        val imageDetailModel = ImageDetailModel()
        imageDetailModel.title = titleText
        imageDetailModel.imageUrl = imageURL

        binding.imageDetail= imageDetailModel
        binding.titleTextView.text = titleText

    }
    companion object {
        @JvmStatic
        @BindingAdapter("viewImageUrl")
        fun viewImageUrl(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get().load(url)
                    .fit()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground).into(view)
            }
        }
    }

}