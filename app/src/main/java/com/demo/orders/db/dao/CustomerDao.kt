package com.demo.orders.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.demo.orders.db.table.CompanyMasterTable
import com.demo.orders.db.table.Customer

@Dao
interface CustomerDao {

    @get:Query("Select * from customer")
    val allItems: CompanyMasterTable

    @Insert(onConflict = REPLACE)
    fun insert(array: ArrayList<Customer>)

    @Update
    fun update(achivementModel: Customer)

    @Query("delete from customer")
    fun delete()

}
