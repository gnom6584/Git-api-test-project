package com.example.gittestproject

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import java.lang.Exception


interface OnSearchQuerySubmitListener {
    fun submitText(searchQuery: String)
}

interface OnRefreshListener{
    fun onRefresh()
}

interface ScrollListener {
    fun onScroll(scrollX: Int, scrollY: Int)
}

interface OnClickListener {
    fun onClick();
}

@BindingAdapter("setSearchListener")
fun setSearchListener(searchView: SearchView, searchListener: OnSearchQuerySubmitListener?) {
    if(searchListener == null)
        return

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            searchView.clearFocus()
            searchListener.submitText(query)
            return true
        }
        override fun onQueryTextChange(newText: String) = true
    })
}

@BindingAdapter("setOnRefreshActionListener")
fun setRefreshActionListener(layout: SwipeRefreshLayout, refreshListener: OnRefreshListener?) {
    if(refreshListener == null)
        return
    layout.setOnRefreshListener {
        refreshListener.onRefresh()
        layout.isRefreshing = false
    }
}

@BindingAdapter("imgUrl")
fun setImageByUrl(imageView: ImageView, url: String?) {
    if(url != null) {
        Glide.with(imageView).load(url).into(imageView)
    }
}

@BindingAdapter("recyclerViewLinearLayoutManager")
fun setRecyclerViewLinearLayoutManager(recyclerView: RecyclerView, orientation: Int?) {
    if(orientation == null)
        return
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, orientation, false)
}

@BindingAdapter("recyclerPosition")
fun setRecyclerPosition(recyclerView: RecyclerView, position: Int?) {
    if(position == null)
        return
    recyclerView.scrollToPosition(position)
}


@BindingAdapter("recyclerViewScrollListener")
fun setRecyclerViewScrollListener(recyclerView: RecyclerView, listener: ScrollListener) {
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            listener.onScroll(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset())
        }
    })
}

@BindingAdapter("setOnClickListener")
fun setOnClickListener(view: View, listener: OnClickListener?) {
    if(listener == null)
        return
    view.setOnClickListener { listener.onClick() }
}

@BindingAdapter("searchViewText")
fun setSearchViewText(searchView: SearchView, text: String?) {
    if(text == null)
        return
    searchView.setQuery(text, false)
}

@BindingAdapter("openUrlOnClick")
fun openUrlOnClick(view: View, url: String?) {
    try {
        if (url == null)
            return
        view.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(view.context, browserIntent, null)
        }
    }
    catch (ex: Exception) {
        Toast.makeText(Application.context, "Invalid url: " + ex.message, Toast.LENGTH_SHORT).show()
    }

}