package com.example.techtask.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.techtask.ConstantDeclarartion
import com.example.techtask.R
import com.example.techtask.databinding.ActivityLoginBinding
import com.example.techtask.databinding.ActivityUserListBinding
import com.example.techtask.model.ModelLoginRequest
import com.example.techtask.viewmodel.UserListViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var userListViewModel: UserListViewModel
    lateinit var progressDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = Dialog(this)
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.setCancelable(false)

        binding.btnLogin.setOnClickListener {
            if (binding.edtEmail.text.toString().isNotEmpty() && binding.edtPassword.text.toString().isNotEmpty()) {
                progressDialog.show()
                doLoginProcess(
                    binding.edtEmail.text.toString(),
                    binding.edtPassword.text.toString()
                )
            } else{
                Toast.makeText(
                    this@LoginActivity,
                    "Username or Password cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun doLoginProcess(email: String, password: String) {
        val userRequest = ModelLoginRequest(email, password)
        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        userListViewModel.getLogin(userRequest).observe(this, Observer { result ->
            if (result) {
                ConstantDeclarartion.saveString(
                    this@LoginActivity,
                    ConstantDeclarartion.LOGIN_STATUS,
                    result.toString()
                )
                progressDialog.hide()
                navigateToHomeScreen()
            } else {
                progressDialog.hide()
                Toast.makeText(
                    this@LoginActivity,
                    "Login with correct credentials!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
        finish()
    }
}