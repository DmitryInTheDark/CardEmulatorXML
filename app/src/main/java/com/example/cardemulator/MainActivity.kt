package com.example.cardemulator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.cardemulator.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy {
    val navHostFragment =
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        
    }
}