package com.example.android_mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.model.DogBreed

class ListViewModel : ViewModel(){
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed("1","bulldog","10", "breedGroup","bredfor","anggry","")
        val dog2 = DogBreed("2","Corgi","10", "breedGroup","bredfor","anggry","")
        val dog3 = DogBreed("3","bullBull","10", "breedGroup","bredfor","anggry","")
        val dog4 = DogBreed("4","bulldong","10", "breedGroup","bredfor","anggry","")
        val dogList = arrayListOf<DogBreed>(dog1, dog2, dog3, dog4)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false

    }
}