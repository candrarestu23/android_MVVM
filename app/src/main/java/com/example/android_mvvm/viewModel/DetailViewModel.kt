package com.example.android_mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.model.DogBreed

class DetailViewModel : ViewModel(){
    val dogLiveData = MutableLiveData<DogBreed>()
    fun fetch(){
        val dog = DogBreed("1","bulldog","10", "breedGroup","bredfor","anggry","")
        dogLiveData.value = dog
    }
}