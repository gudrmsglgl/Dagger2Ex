package com.fastival.dagger2hardex.ui.main.profile


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.fastival.dagger2hardex.R
import com.fastival.dagger2hardex.models.Resource
import com.fastival.dagger2hardex.models.User
import com.fastival.dagger2hardex.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.Exception
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : DaggerFragment() {

    @Inject
    lateinit var provider: ViewModelProviderFactory

    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this, provider).get(ProfileViewModel::class.java)
        } ?: throw Exception ("Invalid Activity")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAuthenticateUser().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Log.d("Main", "onChanged: ProfileFragment: AUTHENTICATED...\n Authenticated as: ${it.data?.email}")
                    it.data?.let { it1 -> setUserDetails(it1) }
                }

                is Resource.Error -> {
                    Log.d("Main", "onChanged: ProfileFragment: ERROR...")
                    it.message?.let { it2 -> setErrorDetails(it2) }
                }

            }
        })
    }

    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = message
        website.text = message
    }

    private fun setUserDetails(user: User) {
        email.text = user.email
        username.text = user.username
        website.text = user.website
    }

}
