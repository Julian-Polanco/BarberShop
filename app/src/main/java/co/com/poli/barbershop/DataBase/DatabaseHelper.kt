package co.com.poli.barbershop.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        private const val TABLE_NAME = "User"
        private const val KEY_NAME = "name"
        private const val KEY_VALUE = "value"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_NAME + " TEXT PRIMARY KEY," + KEY_VALUE + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addToken(name: String, value: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, name)
        values.put(KEY_VALUE, value)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getToken(name: String): String {
        val db = this.readableDatabase
        val cursor = db.query(TABLE_NAME, arrayOf(KEY_VALUE), "$KEY_NAME=?", arrayOf(name), null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(0)
        }
        cursor?.close()
        return ""
    }
    fun deleteToken(tokenKey: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$KEY_NAME = ?", arrayOf(tokenKey))
        db.close()
    }
}