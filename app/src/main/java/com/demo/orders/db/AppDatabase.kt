package com.demo.orders.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.orders.db.dao.*
import com.demo.orders.db.table.*

@Database(
        entities = [
            InvoiceModel::class, LoginTable::class, MobileMenuTable::class, CurrencyTable::class,
            CompanyMasterTable::class, Customer::class,
            Company::class, MessageTable::class, ], version = 2,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun loginDao(): LoginDao
    abstract fun mobileMenuDao(): MobileDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun companyMasterDao(): CompanyMasterDao
    abstract fun companyDao(): CompanyDao
    abstract fun customerDao(): CustomerDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java, "customers.db"
                        )
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

}
