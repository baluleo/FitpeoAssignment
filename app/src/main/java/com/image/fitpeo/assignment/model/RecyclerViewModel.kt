package com.image.fitpeo.assignment.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.image.fitpeo.assignment.api.NetworkHelper
import com.image.fitpeo.assignment.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

// @HiltViewModel will make models to be
// created using Hilt's model factory
// @Inject annotation used to inject all
// dependencies to view model class
@HiltViewModel
class RecyclerViewModel @Inject constructor(
    private val imageRepository: ImageRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    val imageList = MutableLiveData<List<ImagesModel>>()
    val errorMessage = MutableLiveData<String>()
    private val noInternetMsg: String = "No internet connection"

    init {
        loadImagesList()
    }

    fun loadImagesList() {
        if (networkHelper.isNetworkConnected()) {
            val response = imageRepository.getImagesList()

            response.enqueue(object : Callback<List<ImagesModel>> {
                override fun onResponse(
                    call: Call<List<ImagesModel>>,
                    response: Response<List<ImagesModel>>
                ) {
                    imageList.postValue(response.body())
                }
                override fun onFailure(call: Call<List<ImagesModel>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }else{
            errorMessage.postValue(noInternetMsg)
        }
    }
}