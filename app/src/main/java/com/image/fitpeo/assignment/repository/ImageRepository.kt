package com.image.fitpeo.assignment.repository

import com.image.fitpeo.assignment.api.ImagesApi


class ImageRepository constructor(private val imagesApi: ImagesApi) {
    fun getImagesList() = imagesApi.getImagesList()
}