package com.example.flickerdemo

import androidx.room.*

@Dao
interface FlickerDao {

    @Query("SELECT * FROM Flicker  ORDER BY id ASC")
    fun getAllImg(): List<Flicker>

   @Delete
  fun delete(flicker:Flicker)

    //    @Update
    //    fun edit(flicker:Flicker)

    @Insert
    fun insertImg(flicker:Flicker)



}