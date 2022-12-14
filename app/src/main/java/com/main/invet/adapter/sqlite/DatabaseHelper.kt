package com.main.invet.adapter.sqlite
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "invet.db"
        private const val TBL_BOXES = "tbl_boxes"
        private const val ID = "id"
        private const val NAME = "name"
        private const val NUMBER = "number"

        private const val TBL_ENTRIES = "tbl_entries"
        //ID == SAME
        private const val BOXNUMBER = "boxnumber"
        //NAME == same
        private const val COUNT = "count"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblBoxes = ("CREATE TABLE" + TBL_BOXES +"("
                + ID + "INTEGER PRIMARY KEY, " + NAME + "TEXT,"
                + NUMBER + "TEXT" + ")")

        val createTblEntries = ("CREATE TABLE" + TBL_ENTRIES +"("
                + ID + "INTEGER PRIMARY KEY, " + NAME + "TEXT,"
                + COUNT + "TEXT" + ")")


        db?.execSQL(createTblBoxes)
        db?.execSQL(createTblEntries)
    }
    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int){
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_BOXES")
        onCreate(db)
    }

    fun insertBoxWithEntries(std:BoxModel):ArrayList<Long>{
        val successes = ArrayList<Long>()
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID,std.id)
        contentValues.put(NAME,std.name)
        contentValues.put(NUMBER,std.number)


        successes.add(db.insert(TBL_BOXES,null,contentValues))
        for(s in std.inventory){
            val contentValues = ContentValues()
            contentValues.put(ID,s.id)
            contentValues.put(NAME,s.name)
            contentValues.put(BOXNUMBER,s.boxnumber)
            contentValues.put(COUNT,s.count)
            successes.add(db.insert(TBL_ENTRIES,null,contentValues))
        }
        db.close()
        return successes
    }
    fun getAllBoxes(): ArrayList<BoxModel>{
        val boxList: ArrayList<BoxModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_BOXES"
        val db = this.readableDatabase
        val cursor: Cursor?
        var entriesCursor: Cursor? = null
        try{
            cursor=db.rawQuery(selectQuery,null)

        } catch (e: java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var number:Int



        var eid:Int
        var ename:String
        var ecount:Int
        var boxnumber:Int

        var entries = ArrayList<BoxModelEntry>()
        if(cursor.moveToFirst()){
            do{

                entries = ArrayList<BoxModelEntry>()
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                number = cursor.getInt(cursor.getColumnIndexOrThrow("number"))
                try{
                    entriesCursor=db.rawQuery("SELECT * FROM $TBL_ENTRIES WHERE $BOXNUMBER == $number",null)

                } catch (e: java.lang.Exception){
                    e.printStackTrace()
                }
                if(entriesCursor!!.moveToFirst()){
                    do{
                        eid = entriesCursor.getInt(entriesCursor.getColumnIndexOrThrow("id"))
                        ename = entriesCursor.getString(entriesCursor.getColumnIndexOrThrow("name"))
                        ecount = entriesCursor.getInt(entriesCursor.getColumnIndexOrThrow("count"))
                        boxnumber = entriesCursor.getInt(entriesCursor.getColumnIndexOrThrow("boxnumber"))
                        entries.add(BoxModelEntry(ename,ecount,eid,boxnumber))
                    }while(cursor.moveToNext())
                }


                val std = BoxModel(id = id,name=name,number=number, inventory = entries)
                boxList.add(std)

            }while (cursor.moveToNext())
        }
        entriesCursor!!.close()
        cursor.close()
        return boxList
    }
}