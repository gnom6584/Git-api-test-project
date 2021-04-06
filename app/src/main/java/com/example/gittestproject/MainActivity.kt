package com.example.gittestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gittestproject.di.ActivityModel
import com.example.gittestproject.di.DaggerActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var onBackPressedListener : OnBackPressedListener? = null

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerActivityComponent.builder().activityModel(ActivityModel(this)).build().inject(this)
    }

    override fun onBackPressed() {
        onBackPressedListener?.onBackPressed()
    }
}