package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.CurrencyTable
import com.demo.orders.db.table.LoginTable

@Dao
interface CurrencyDao {

    @get:Query("Select * from currency")
    val allItems: List<LoginTable>

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<CurrencyTable>)

    @Update
    fun update(achivementModel: CurrencyTable)

    @Query("Delete from currency")
    fun delete()

}
