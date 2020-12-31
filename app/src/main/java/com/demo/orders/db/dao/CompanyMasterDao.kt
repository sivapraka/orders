package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.CompanyMasterTable

@Dao
interface CompanyMasterDao {

    @get:Query("Select * from company_master")
    val allItems: CompanyMasterTable

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<CompanyMasterTable>)

    @Update
    fun update(achivementModel: CompanyMasterTable)

    @Query("delete from company_master")
    fun delete()

}
