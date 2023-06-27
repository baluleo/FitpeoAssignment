@file:Suppress("UNCHECKED_CAST")

package com.image.fitpeo.assignment.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.image.fitpeo.assignment.api.NetworkHelper
import com.image.fitpeo.assignment.repository.ImageRepository


class ViewModelFactory constructor(private val repository: ImageRepository, private val networkHelper: NetworkHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            RecyclerViewModel(this.repository,this.networkHelper) as T
        } else
            throw IllegalArgumentException("ViewModel Not Found")
        }
}