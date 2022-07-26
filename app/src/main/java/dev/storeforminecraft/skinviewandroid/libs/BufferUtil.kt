package dev.storeforminecraft.skinviewandroid.libs

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

object BufferUtil {
    fun createFloatBuffer(array: FloatArray) : FloatBuffer{
        // (number of coordinate values * 4 bytes per float)
        return ByteBuffer.allocateDirect(array.size * 4).run {
            // use the device hardware's native byte order
            order(ByteOrder.nativeOrder())

            // create a floating point buffer from the ByteBuffer
            asFloatBuffer().apply {
                // add the coordinates to the FloatBuffer
                put(array)
                // set the buffer to read the first coordinate
                position(0)
            }
        }
    }

    fun createShortBuffer(array: ShortArray) : ShortBuffer{
        // (# of coordinate values * 2 bytes per short)
        return ByteBuffer.allocateDirect(array.size * 2).run {
            order(ByteOrder.nativeOrder())
            asShortBuffer().apply {
                put(array)
                position(0)
            }
        }
    }
}