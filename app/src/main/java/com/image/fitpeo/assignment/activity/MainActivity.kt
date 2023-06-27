package com.image.fitpeo.assignment.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.image.fitpeo.assignment.adapter.ImageListAdapter
import com.image.fitpeo.assignment.api.ImagesApi
import com.image.fitpeo.assignment.api.NetworkHelper
import com.image.fitpeo.assignment.databinding.ActivityMainBinding
import com.image.fitpeo.assignment.model.ImagesModel
import com.image.fitpeo.assignment.model.OnClickListener
import com.image.fitpeo.assignment.model.RecyclerViewModel
import com.image.fitpeo.assignment.model.ViewModelFactory
import com.image.fitpeo.assignment.repository.ImageRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val _TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    @Inject  lateinit var viewModel: RecyclerViewModel
    private val retrofitService = ImagesApi.getInstance()

    @Inject var adapter = ImageListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory(ImageRepository(retrofitService),NetworkHelper(this)))[RecyclerViewModel::class.java]
        binding.imageList.adapter = adapter
        viewModel.imageList.observe(this) {
            Log.d(_TAG, "onCreate: $it")
            binding.listError.visibility = View.INVISIBLE
            binding.loadingView.visibility = View.INVISIBLE
            binding.imageList.visibility = View.VISIBLE
            adapter.setImageList(it)
        }
        viewModel.errorMessage.observe(this) {
            Log.d(_TAG, "OnError: $it")
            binding.loadingView.visibility = View.INVISIBLE
            binding.imageList.visibility = View.INVISIBLE
            binding.listError.text = it.toString()
        }
        viewModel.loadImagesList()

        // Applying OnClickListener to our Adapter
        adapter.setOnClickListener { _, model ->
            val intent = Intent(this@MainActivity, ImageDetailActivity::class.java)
            // Passing the data to the ImageDetailActivity
            intent.putExtra(IMAGE_URL, model.url)
            intent.putExtra(TITLE, model.title)
            startActivity(intent)
        }

    }
    companion object{
        const val IMAGE_URL="ImageURL"
        const val TITLE="title"
    }

}