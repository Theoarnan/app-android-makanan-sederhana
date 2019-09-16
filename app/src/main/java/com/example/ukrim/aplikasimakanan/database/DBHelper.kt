package com.example.ukrim.aplikasimakanan.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.ukrim.aplikasimakanan.database.entity.MealEntity
import org.jetbrains.anko.db.*

class DBHelper(ctx: Context):ManagedSQLiteOpenHelper(ctx, "MealDB.db", null, 1) {
    companion object {
        private var instance : DBHelper?= null

        @Synchronized
        fun getInstance(ctx: Context): DBHelper {
            if(instance == null){
                instance = DBHelper(ctx.applicationContext)
            }
            return instance as DBHelper
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(MealEntity.TABLE_FAVORITE, true,
            MealEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MealEntity.ID_MEAL to TEXT + NOT_NULL,
            MealEntity.NAME_MEAL to TEXT,
            MealEntity.CATEGORY_MEAL to TEXT,
            MealEntity.INSTRUCTION_MEAL to TEXT,
            MealEntity.IMAGE_MEAL to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(MealEntity.TABLE_FAVORITE)
    }


}

//Access property for Context
val Context.database:DBHelper
    get() = DBHelper.getInstance(applicationContext)