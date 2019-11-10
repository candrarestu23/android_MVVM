package com.example.android_mvvm.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import com.example.android_mvvm.R
import com.example.android_mvvm.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private var uid = 0
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch()
        arguments?.let {
            uid = DetailFragmentArgs.fromBundle(it).uuid
        }
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.dogLiveData.observe(this, Observer {dog ->
            dog?.let {
                tvName.text = dog.dogBreed
                tvPurpose.text = dog.bredFor
                tvTemprament.text = dog.temperament
                tvLifeSpan.text = dog.lifeSpan
            }
        })
    }
}
