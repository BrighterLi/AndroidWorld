package com.xiaoming.a010kotlin.xiangxuedemo.modules.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xiaoming.a010kotlin.R
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 总控制器：设置导航
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_collect,
                R.id.navigation_personal)
                .build()

        val navController: NavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(navView, navController)
    }
}
