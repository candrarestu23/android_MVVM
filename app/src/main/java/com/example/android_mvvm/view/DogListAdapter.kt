package com.example.android_mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mvvm.R
import com.example.android_mvvm.model.DogBreed
import kotlinx.android.synthetic.main.item_list.view.*

class DogListAdapter(val dogList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    fun updateDogList(newDogsList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
       return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.tvName.text = dogList[position].dogBreed
        holder.view.tvLifeSpan.text = dogList[position].lifeSpan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}