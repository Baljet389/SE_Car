//This exist only to make the RaspberryConnection class a singleton
package com.example.controlrccar

object ConnectionManager {
    @JvmField
    var connection: RaspberryConnection?=null

}