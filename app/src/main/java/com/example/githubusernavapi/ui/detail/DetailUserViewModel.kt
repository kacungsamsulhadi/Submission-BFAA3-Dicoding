package com.example.githubusernavapi.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubusernavapi.api.RetrofitClient
import com.example.githubusernavapi.data.local.FavUser
import com.example.githubusernavapi.data.local.FavUserDao
import com.example.githubusernavapi.data.local.UserDB
import com.example.githubusernavapi.data.model.DetailUserRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailUserRes>()

    private var userDao: FavUserDao?
    private var userDB: UserDB?

    init {
        userDB = UserDB.getDB(application)
        userDao = userDB?.favUserDao()
    }

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserRes> {
                override fun onResponse(
                    call: Call<DetailUserRes>,
                    response: Response<DetailUserRes>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserRes>, t: Throwable) {
                    Log.d("failure", t.message.toString())
                }
            })
    }

    fun getUserDetail(): LiveData<DetailUserRes> {
        return user
    }

    fun addToFavorite(username: String, id: Int, avatarUrl: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = FavUser(
                username,
                id,
                avatarUrl
            )
            userDao?.addToFavorite(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeFromFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.removeFromFav(id)
        }
    }
}