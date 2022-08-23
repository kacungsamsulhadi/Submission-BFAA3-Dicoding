package com.example.githubusernavapi.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.githubusernavapi.data.local.FavUser
import com.example.githubusernavapi.data.local.FavUserDao
import com.example.githubusernavapi.data.local.UserDB

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var userDao: FavUserDao?
    private var userDB: UserDB?

    init {
        userDB = UserDB.getDB(application)
        userDao = userDB?.favUserDao()
    }

    fun getFavUser(): LiveData<List<FavUser>>? {
        return userDao?.getFavUser()
    }
}