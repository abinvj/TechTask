package com.example.techtask.view

import android.app.Dialog
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.techtask.ConstantDeclarartion
import com.example.techtask.R
import com.example.techtask.adapter.UserListAdapter
import com.example.techtask.databinding.ActivityUserListBinding
import com.example.techtask.model.Data
import com.example.techtask.model.ModelUserListResponse
import com.example.techtask.viewmodel.UserListViewModel
import java.util.Locale

class UserListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserListBinding
    lateinit var userListViewModel : UserListViewModel
    lateinit var userListAdapter : UserListAdapter
    lateinit var progressDialog : Dialog


    lateinit var listUsers : List<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_user_list)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = Dialog(this)
        progressDialog.setContentView(R.layout.dialog_progress)
        progressDialog.setCancelable(false)

        progressDialog.show()

        binding.edtSearch.addTextChangedListener(watcher)

        userListViewModel = ViewModelProvider(this).get(UserListViewModel::class.java)
        userListViewModel.getUser(2)!!.observe(this, Observer { result ->
            progressDialog.hide()
            listUsers = result.data
            userListAdapter.setData(result.data)
        } )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        userListAdapter = UserListAdapter(this)
        binding.recyclerView.adapter = userListAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                ConstantDeclarartion.saveString(this@UserListActivity,
                    ConstantDeclarartion.LOGIN_STATUS,"")
                navigateToLoginScreen()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    val watcher : TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            //filterArray(edtSearch.text.toString())
            if(binding.edtSearch.text.toString().isEmpty())
                userListAdapter.setData(listUsers)
            else
                filterArray(p0.toString())
        }

    }
    fun filterArray(search : String) {
        val filterArr = filter(search)
        userListAdapter.setData(filterArr)
    }

    private fun filter(search : String) : List<Data>{

        val filteredlist = ArrayList<Data>()

        try {
            for (items in listUsers){
                val name : String = items.first_name.toLowerCase(Locale.ROOT)
                val email : String = items.email.toLowerCase(Locale.ROOT)
                if (email.contains(search) || name.contains(search)){
                    filteredlist.add(items)
                }
            }
            Log.i("filteredlist", "size ${filteredlist.size} and $filteredlist")

        }catch (e: Exception){
            e.printStackTrace()
        }

        return filteredlist
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}