package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.SchemeMasterTable

@Dao
interface SchemeMasterDao {

    @get:Query("Select * from scheme_master")
    val allItems: List<SchemeMasterTable>

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<SchemeMasterTable>)

    @Update
    fun update(achivementModel: SchemeMasterTable)

    @Query("delete from scheme_master")
    fun delete()
}
