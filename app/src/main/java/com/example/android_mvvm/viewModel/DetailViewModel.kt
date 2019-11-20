package com.example.android_mvvm.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.model.DogBreed
import com.example.android_mvvm.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application
) : BaseViewModel(application){
    val dogLiveData = MutableLiveData<DogBreed>()
    fun fetch(uuid: Int){
        launch {
            Log.d("test3", uuid.toString())
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }
    }
}