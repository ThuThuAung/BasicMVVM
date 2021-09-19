package com.thuthu.basicmvvm.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thuthu.basicmvvm.R
import com.thuthu.basicmvvm.data.model.UserResponse
import com.thuthu.basicmvvm.view.adapters.ListAdapter
import com.thuthu.basicmvvm.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.list_layout.*
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*


class ListActivity : AppCompatActivity() {

    private var adapter = ListAdapter(mutableListOf()) { user ->  displayDetailView(user) }

    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listRecyclerView.adapter = adapter
        loadData()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        listRecyclerView.isEnabled = false
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        listRecyclerView.isEnabled = true
    }

    private fun loadData() {
        showLoading()
        viewModel.loadList().observe(this, Observer { list ->
            hideLoading()
            if (list == null) {
                showMessage()
            } else {
                adapter.setList(list)
            }
        })
    }

    private fun showMessage() {
        Toast.makeText(this, " Network error! ", Toast.LENGTH_LONG).show()
    }

    private fun displayDetailView(user: UserResponse) {

        if(user != null)
        {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("userDetailData", user)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.logout) {
            //process your onClick here
            val sharedPref = getSharedPreferences("RememberLogin", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("rememberLogin", false)
            editor.commit()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            true
        } else super.onOptionsItemSelected(item)
    }
}