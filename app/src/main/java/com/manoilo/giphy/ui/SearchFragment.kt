package com.manoilo.giphy.ui

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.manoilo.giphy.R
import com.manoilo.giphy.binding.FragmentDataBindingComponent
import com.manoilo.giphy.databinding.SearchFragmentBinding
import com.manoilo.giphy.di.Injectable
import com.manoilo.giphy.testing.OpenForTesting
import com.manoilo.giphy.ui.common.GifListAdapter
import com.manoilo.giphy.utils.AppExecutors
import javax.inject.Inject

@OpenForTesting
class SearchFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private lateinit var binding: SearchFragmentBinding

    private lateinit var adapter: GifListAdapter

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false,
            dataBindingComponent
        )
        binding.searchBar.input.setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch(view)
                true
            } else {
                false
            }
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdapter = GifListAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        )
        binding.photoList.adapter = rvAdapter
        adapter = rvAdapter

        viewModel.results.observe(viewLifecycleOwner, Observer { result ->
            result?.apply {
                if (isNotEmpty())
                    adapter.submitList(this)
            }
        })
    }


    private fun doSearch(v: View) {
        val query = binding.searchBar.input.text.toString()
        dismissKeyboard(v.windowToken)
        viewModel.searchGifs(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}
