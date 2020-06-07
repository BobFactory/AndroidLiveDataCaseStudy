package com.bawender.creations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loginModel by viewModels<LoginViewModel>() // Lazy init of ViewModels.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUserEmail.addTextChangedListener { email ->
            loginModel.updateUserEmail(email.toString())
        }

        etUserPassword.addTextChangedListener { password ->
            loginModel.updateUserPassword(password.toString())
        }

        //Main Observer that enables and disables the login button.
        loginModel.loginEnabled.observe(this, Observer { enabled ->
            btnLogin.isEnabled = enabled
        })

    }

}
