//This exist only to make the RaspberryConnection class a singleton
package com.car482.android

object ConnectionManager {
    @JvmField
    var connection: RaspberryConnection?=null

}