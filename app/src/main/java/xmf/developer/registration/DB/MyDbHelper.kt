package xmf.developer.registration.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import xmf.developer.registration.models.MyShablon

class MyDbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    MyDbHelperInterface {
    companion object {
        const val DB_NAME = "d_name"
        const val DB_VERSION = 1
        const val ID = "id"
        const val MYSHABLON_TABLE = "shablon_table"
        const val MYSHABLON_FULL_NAME = "shablon_name"
        const val MYSHABLON_NUMBER = "shablon_number"
        const val MYSHABLON_ADDRESS = "shablon_address"
        const val MYSHABLON_COUNTRY_FLAG = "shablon_country"
        const val MYSHABLON_PASSWORD = "shablon_password"
        const val MYSHABLON_IMAGE = "shablon_image"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val contactQuery =
            "create table $MYSHABLON_TABLE($ID integer not null primary key autoincrement unique, $MYSHABLON_FULL_NAME text not null, $MYSHABLON_NUMBER text not null, $MYSHABLON_ADDRESS text not null, $MYSHABLON_PASSWORD text not null,$MYSHABLON_IMAGE integer not null)"
        p0?.execSQL(contactQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
    override fun addShablon(myShablon: MyShablon) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MYSHABLON_FULL_NAME, myShablon.fullName)
        contentValues.put(MYSHABLON_IMAGE,myShablon.image)
        contentValues.put(MYSHABLON_NUMBER, myShablon.number)
        contentValues.put(MYSHABLON_ADDRESS, myShablon.address)
        contentValues.put(MYSHABLON_PASSWORD, myShablon.password)
        database.insert(MYSHABLON_TABLE, null, contentValues)
        database.close()

    }

    override fun getAllShablons(): ArrayList<MyShablon> {
        val database = this.writableDatabase
        val cursor = database.rawQuery("select * from $MYSHABLON_TABLE", null)
        val list = ArrayList<MyShablon>()
        if (cursor.moveToFirst()) {
            do {
                list.add(
                    MyShablon(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}
