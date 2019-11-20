package com.example.android_mvvm.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.android_mvvm.R
import com.example.android_mvvm.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()
        rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogListAdapter
        }
        srList.setOnRefreshListener {
            rvList.visibility = View.GONE
            tvError.visibility = View.GONE
            pbList.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            srList.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogs.observe(this, Observer {
            it?.let {
                rvList.visibility = View.VISIBLE
                dogListAdapter.updateDogList(it)
            }
        })

        viewModel.dogsLoadError.observe(this, Observer { isError ->
            isError?.let{
                tvError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                pbList.visibility = if(it) View.VISIBLE else View.GONE

                if(it){
                    tvError.visibility = View.GONE
                    rvList.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionSetting -> {
                view?.let {
                    Navigation.findNavController(it).navigate(ListFragmentDirections.actionSetting())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
