package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.LoginTable
import com.demo.orders.db.table.MobileMenuTable

@Dao
interface MobileDao {

    @get:Query("Select * from mobile_menu")
    val allItems: List<LoginTable>

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<MobileMenuTable>)

    @Update
    fun update(achivementModel: MobileMenuTable)

    @Query("Delete from mobile_menu")
    fun delete()

    @Query("Select * From mobile_menu where screenType=:type  and languageCode=:code")
    fun menu(type: String, code: String): List<MobileMenuTable>

}
