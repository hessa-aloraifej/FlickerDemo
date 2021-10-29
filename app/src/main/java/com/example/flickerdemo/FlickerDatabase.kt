package com.example.flickerdemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Flicker::class],version = 1,exportSchema = false)
abstract class FlickerDatabase: RoomDatabase() {

    companion object{
        var instance:FlickerDatabase?=null;
        fun getInstance(ctx: Context):FlickerDatabase
        {
            if(instance!=null)
            {
                return  instance as FlickerDatabase;
            }
            instance= Room.databaseBuilder(ctx,FlickerDatabase::class.java,"somename").run { allowMainThreadQueries() }.build();
            return instance as FlickerDatabase;
        }
    }
    abstract fun FlickerDao():FlickerDao;
}