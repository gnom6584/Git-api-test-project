package com.example.gittestproject.searchscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gittestproject.*
import com.example.gittestproject.databinding.FragmentSearchBinding
import com.example.gittestproject.searchscreen.di.DaggerSearchFragmentComponent
import com.example.gittestproject.searchscreen.di.SearchFragmentModule
import javax.inject.Inject


class SearchFragment : Fragment(), UserLoginReceiver {

    companion object {
        const val PAGE_SIZE = 25

        fun newInstance() =
            SearchFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var searchView : SearchView

    @Inject
    lateinit var rootLayout: LinearLayout

    @Inject
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var scrollOnTop: Button

    @Inject
    lateinit var lifecycleOwner: LifecycleOwner

    @Inject
    lateinit var navigationController : NavigationController

    fun getRecyclerPosition() = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

    fun updateScrollOnTopButton(scrollY: Int) {
        scrollOnTop.visibility = when {
            scrollY / searchView.measuredHeight < 1 -> View.GONE
            else -> View.VISIBLE
        }
    }

    fun updateList(adapter: SearchListAdapter, pagedList: PagedList<RepositoryViewModel>) {
        with(adapter) {
            submitList(pagedList)
            recyclerView.adapter = this
        }
    }

    fun onRecyclerScroll(scrollX: Int, scrollY: Int) {
        updateScrollOnTopButton(scrollY)
        viewModel.listPosition = getRecyclerPosition()
    }

    fun onScrollToTopClicked() = recyclerView.scrollToPosition(0)

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {

        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false).apply {
            fragment = this@SearchFragment
        }

        val component = DaggerSearchFragmentComponent.builder()
            .searchFragmentModule(SearchFragmentModule(this, binding))
            .build()

        component.inject(this)

        binding.viewModel = viewModel

        viewModel.pagedList.observe(lifecycleOwner, Observer { pagedList ->
            updateList(component.getAdapter(), pagedList)
        })

        return binding.root;
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(!::recyclerView.isInitialized)
            return

        recyclerView.layoutManager?.let {
            outState.putParcelable(
                "RecyclerStateKey",
                it.onSaveInstanceState()
            )
        }
    }

    override fun receiveUserLogin(login: String) {
        navigationController.navigateFromSearchToUserFragment()
        viewModel.selectUser(login)
    }
}