package com.example.githubusernavapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavUser::class],
    version = 1
)
abstract class UserDB: RoomDatabase() {
    companion object{
        var INSTANCE : UserDB? = null

        fun getDB(context: Context): UserDB? {
            if (INSTANCE == null){
                synchronized(UserDB::class){
                INSTANCE = Room.databaseBuilder(context.applicationContext, UserDB::class.java, "user_database").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun favUserDao(): FavUserDao
}