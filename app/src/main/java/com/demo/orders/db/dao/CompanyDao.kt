package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.Company

@Dao
interface CompanyDao {

    @get:Query("Select * from company")
    val allItems: Company

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<Company>)

    @Update
    fun update(achivementModel: Company)

    @Query("delete from company")
    fun delete()

}
