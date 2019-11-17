package com.fastival.dagger2hardex.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.fastival.dagger2hardex.R
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.ui.BaseActivity
import com.fastival.dagger2hardex.ui.main.MainActivity
import com.fastival.dagger2hardex.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : BaseActivity(), View.OnClickListener {

    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun getLayoutResId(): Int {
        return R.layout.activity_auth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_auth)
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()

        login_button.setOnClickListener(this)

        listenObserveData()
    }

    private fun listenObserveData() {
        viewModel.observeUser().observe(this, Observer {
            if (it != null) {
                when (it) {
                    is Resource.Loading -> showProgressBar(true)
                    //AuthResource.AuthStatus.LOADING -> showProgressBar(true)
                    is Resource.Success -> { showProgressBar(false)
                        if (it.data != null) {
                            Log.d("Main", "onChanged: Login Success: ${it.data.email}")
                            onLoginSuccess(Pair("email",it.data.email), Pair("username",it.data.username), Pair("website",it.data.website))
                        }
                    }
                    /*AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d("Main", "onChanged: Login Success: ${it.data?.email}")
                    }*/
                    is Resource.Error -> {
                        Log.d("Main", "onChanged: Login Success: ${it.message}")
                        showProgressBar(false)
                        showToast("${it.message}\nDid you enter a number between 0 .. 10?")
                    }
                    /*AuthResource.AuthStatus.ERROR -> {
                        Log.d("Main", "onChanged: Login Success: ${it.message}")
                        showProgressBar(false)
                        showToast("${it.message}\nDid you enter a number between 0 .. 10?")
                    }*/
                    else -> showProgressBar(false)
                }

            }

        })
    }

    private fun onLoginSuccess(vararg values: Pair<String, String>){
        /*val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()*/
        navActivity<MainActivity>(this, true, *values)
    }

    fun setLogo() {
        requestManager.load(logo)
            .into(login_logo)
    }

    override fun onClick(p0: View) {

        when(p0.id) {
             R.id.login_button -> attemptLogin()
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        viewModel.authenticateWithId(user_id_input.text.toString().toInt())
    }

    private fun showProgressBar(b: Boolean) {

    }

}
