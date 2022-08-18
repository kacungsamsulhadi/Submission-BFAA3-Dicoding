package com.example.githubusernavapi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusernavapi.api.RetrofitClient
import com.example.githubusernavapi.data.model.DetailUserRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel(){
    val user = MutableLiveData<DetailUserRes>()

    fun setUserDetail(username: String){
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DetailUserRes> {
                override fun onResponse(
                    call: Call<DetailUserRes>,
                    response: Response<DetailUserRes>
                ) {
                    if (response.isSuccessful){
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailUserRes>, t: Throwable) {
                    Log.d("failure", t.message.toString())
                }

            })
    }
    fun getUserDetail(): LiveData<DetailUserRes>{
        return user
    }
}