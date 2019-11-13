package com.example.android_mvvm.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_mvvm.model.DogApiService
import com.example.android_mvvm.model.DogBreed
import com.example.android_mvvm.model.DogDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application){

    private val dogService = DogApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            dogService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(t: List<DogBreed>) {
                        storeDogsLocaly(t)
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun dogRetrieve(dogList: List<DogBreed>){
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocaly(list: List<DogBreed>){
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAlldogs()
            val result = dao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogRetrieve(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}