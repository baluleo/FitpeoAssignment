package com.image.fitpeo.assignment.activity

import android.annotation.SuppressLint
import com.image.fitpeo.assignment.api.NetworkHelper
import com.image.fitpeo.assignment.model.RecyclerViewModel
import com.image.fitpeo.assignment.repository.ImageRepository
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ImageViewModelTest {
    private val mockCarRepository: ImageRepository = mock(ImageRepository::class.java)
    private val networkHelper:NetworkHelper = mock(NetworkHelper::class.java)

    @Test
    fun validateImageList(){
        val carViewModel = RecyclerViewModel(mockCarRepository,networkHelper)
        verify(mockCarRepository).getImagesList()
                    val imageList =  mockCarRepository.getImagesList()
       val result = verify(carViewModel.loadImagesList())
        assertEquals(0,listOf(imageList))
    }

}