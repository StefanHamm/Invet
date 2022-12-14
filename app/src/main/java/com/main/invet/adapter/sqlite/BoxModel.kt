package com.main.invet.adapter.sqlite
import java.util.*
import kotlin.collections.*
data class BoxModelEntry(var name:String="",var count:Int=0,var id:Int = getAutoId(),var boxnumber:Int =0){
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}

data class BoxModel(
    var id: Int =getAutoId(),
    var name: String = "",
    var number: Int = 0,
    var inventory:ArrayList<BoxModelEntry> = ArrayList()
){
    companion object {
        fun getAutoId(): Int {
            val random = Random()
            return random.nextInt(100)
        }
    }
}