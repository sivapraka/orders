package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.LoginTable

@Dao
interface LoginDao {

    @get:Query("Select * from login")
    val allItems: LoginTable

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<LoginTable>)

    @Update
    fun update(achivementModel: LoginTable)

    @Query("delete from login")
    fun delete()

}
