package com.example.android_mvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mvvm.R
import com.example.android_mvvm.databinding.ItemListBinding
import com.example.android_mvvm.model.DogBreed
import com.example.android_mvvm.util.getProgressDrawable
import com.example.android_mvvm.util.loadImage
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.tvLifeSpan
import kotlinx.android.synthetic.main.item_list.view.tvName

class DogListAdapter(val dogList: ArrayList<DogBreed>) : RecyclerView.Adapter<DogListAdapter.DogViewHolder>(), ClickListener {

    fun updateDogList(newDogsList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
       return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogList[position]
        holder.view.listener = this
//        holder.view.tvName.text = dogList[position].dogBreed
//        holder.view.tvLifeSpan.text = dogList[position].lifeSpan
//        holder.view.setOnClickListener {
//            val action = ListFragmentDirections.actionDetailFragment()
//            action.uuid = dogList[position].uuid
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.ivItem.loadImage(dogList[position].imageUrl, getProgressDrawable(holder.view.ivItem.context))
    }

    override fun onDogClicked(v: View) {
        val uuid = v.dogID.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.uuid = uuid
        Navigation.findNavController(v).navigate(action)
    }

    class DogViewHolder(var view: ItemListBinding) : RecyclerView.ViewHolder(view.root)
}