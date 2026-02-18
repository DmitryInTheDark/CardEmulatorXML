package com.example.cardemulator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.cardemulator.app.CardEmulatorApp
import com.example.cardemulator.databinding.ActivityMainBinding
import com.example.domain.use_case.auth.AuthUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var authUseCase: AuthUseCase

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy {
    val navHostFragment =
        supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }
    private val navController2 by lazy {
        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        (application as CardEmulatorApp).appComponent.inject(this)
        lifecycleScope.launch(Dispatchers.IO) {
            if (authUseCase.getCurrentUser() != null){
                withContext(Dispatchers.Main){
                    navController.navigate(R.id.mainFragment)
                }
            }
            else{
                withContext(Dispatchers.Main){
                    navController.setGraph(R.navigation.auth_graph)
                }
            }
            authUseCase.getAllUsers()
        }
    }

    fun navBack(){
        binding.vp.displayedChild = 1
    }
}