package com.fastival.dagger2hardex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.fastival.dagger2hardex.R
import com.fastival.dagger2hardex.ui.BaseActivity
import com.fastival.dagger2hardex.ui.main.posts.PostsFragment
import com.fastival.dagger2hardex.ui.main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        //testFragment()
    }

    private fun init(){
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }
    /*private fun testFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, PostsFragment())
            .commit()
    }*/


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId){
        R.id.logout -> {
            sessionManager.logOut()
            true
        }

        android.R.id.home -> {
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                Log.d("Main", "MainActivity: drawer_layout.isDrawerOpen(GravityCompat.START)")
                drawer_layout.closeDrawer(GravityCompat.START)
                true
            }
            else {
                Log.d("Main", "MainActivity: drawer_layout.isDrawerOpen(GravityCompat.START) not true")
                false
            }
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        when(menu.itemId) {
            R.id.nav_profile -> {
                val opt = NavOptions.Builder().setPopUpTo(R.id.profileScreen, true).build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileScreen, null, opt)
            }
            R.id.nav_posts -> {
                if (isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen)
                }
            }
        }
        menu.setChecked(true)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun isValidDestination(@IdRes destination: Int): Boolean {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("Main", "MainActivity: onSupportNavigateUp()")
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawer_layout)
    }
}
