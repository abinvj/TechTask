package com.example.techtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.techtask.ConstantDeclarartion
import com.example.techtask.R

class CheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        val isLoggedIn = ConstantDeclarartion.getString(
            this@CheckActivity,
            ConstantDeclarartion.LOGIN_STATUS, ""
        )

        if (isLoggedIn == "true") {
            navigateToHomeScreen()
        } else {
            navigateToLoginScreen()
        }
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}