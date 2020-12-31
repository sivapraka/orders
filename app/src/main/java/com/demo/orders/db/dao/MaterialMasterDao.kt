package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.MaterialMasterTable

@Dao
interface MaterialMasterDao {

    @get:Query("Select * from material_master")
    val allItems: List<MaterialMasterTable>

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<MaterialMasterTable>)

    @Update
    fun update(achivementModel: MaterialMasterTable)

    @Query("delete from material_master")
    fun delete()
}
