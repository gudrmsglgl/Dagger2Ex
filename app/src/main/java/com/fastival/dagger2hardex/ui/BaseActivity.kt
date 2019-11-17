package com.fastival.dagger2hardex.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import com.fastival.dagger2hardex.SessionManager
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.ui.auth.AuthActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    companion object{
        const val EXIT_INTERVAL_TIME = 2000
    }

    private var pressedTime:Long = 0

    @LayoutRes
    abstract fun getLayoutResId(): Int

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            when(it) {
                is Resource.Init -> Log.d("Main", "onChanged: BaseActivity: Init...")
                is Resource.Loading -> Log.d("Main", "onChanged: BaseActivity: LOADING...")
                is Resource.Success -> Log.d("Main", "onChanged: BaseActivity: Authenticated as: ${it.data?.email}")
                is Resource.Error -> Log.d("Main", "onChanged: BaseActivity: Error...")
                is Resource.Logout -> {
                    Log.d("Main", "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login Screen")
                    navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen(){
        navActivity<AuthActivity>(this,true)
        sessionManager.getAuthUser().value = Resource.Init()
    }

    fun showToast(msg: String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T> navActivity(context: Context,  isFinish: Boolean, vararg values: Pair<String, String>) {
        val intent = Intent(context, T::class.java).also {
            if (values.isNotEmpty()) {
                values.forEach { pair ->
                    it.putExtra(pair.first, pair.second)
                }
            }
        }
        startActivity(intent)
        if (isFinish) finish()
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        val intervalTime = currentTime - pressedTime

        if (intervalTime in 0..EXIT_INTERVAL_TIME) {
            super.onBackPressed()
        } else {
            pressedTime = currentTime
            showToast("앱을 종료하시려면 한번 더 눌러주세요.")
        }
    }
}