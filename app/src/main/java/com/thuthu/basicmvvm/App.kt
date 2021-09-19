package com.thuthu.basicmvvm

import android.app.Application
import  com.thuthu.basicmvvm.data.db.UserDatabase
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

lateinit var db: UserDatabase

class App : Application() {

    companion object {
        lateinit var INSTANCE: App
    }


    init {
        INSTANCE = this
    }

    lateinit var cicerone: Cicerone<Router>

    override fun onCreate() {
        super.onCreate()
        db = UserDatabase.getInstance(this)
        INSTANCE = this
        this.initCicerone()

    }

    private fun initCicerone() {
        this.cicerone = Cicerone.create()
    }


}