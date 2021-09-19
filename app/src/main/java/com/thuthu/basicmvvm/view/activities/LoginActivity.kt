package com.thuthu.basicmvvm.view.activities

import android.R.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thuthu.basicmvvm.R
import com.thuthu.basicmvvm.databinding.ActivityLoginBinding
import com.thuthu.basicmvvm.viewmodel.UserViewModel
import java.util.*


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    var emailList: Array<String> = arrayOf("jed@gmail.com", "stephen@gmail.com", "james@gmail.com")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
            this,
            R.layout.activity_login
        )
        val spinner: Spinner = findViewById(R.id.countriesList)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.onInsertUserData(emailList)
        configureLiveDataObservers()

        viewModel.getUserData().observe(this, androidx.lifecycle.Observer { users ->

            val sharedPref = getSharedPreferences("RememberLogin", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putBoolean("rememberLogin", viewModel.onChecked)
            editor.commit()

            startActivity(Intent(this, ListActivity::class.java))
            finish()

        })

        viewModel.getError().observe(this, androidx.lifecycle.Observer { error ->
            Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
        })


        viewModel.fetchSpinnerItems(onCountriesList()).observe(
          this,
          androidx.lifecycle.Observer { spinnerData ->
              val arrAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(
                  this,
                  R.layout.spinner_item_layout,
                  spinnerData
              )
              arrAdapter.setDropDownViewResource(R.layout.spinner_item_layout)
              spinner.adapter = arrAdapter
          })

    }

    private fun onCountriesList(): ArrayList<String> {
        val locales = Locale.getAvailableLocales()
        val countries = ArrayList<String>()
        for (locale in locales) {
            val country = locale.getDisplayCountry()
            if (country.trim({ it <= ' ' }).length > 0 && !countries.contains(country)) {
                countries.add(country)
            }
        }
        Collections.sort(countries)

        return countries

    }

    private fun configureLiveDataObservers() {

        viewModel.getInsertLiveData().observe(this, androidx.lifecycle.Observer { saved ->
            if (saved) {
                System.out.println(("Saved"))
            }
        })

    }

}