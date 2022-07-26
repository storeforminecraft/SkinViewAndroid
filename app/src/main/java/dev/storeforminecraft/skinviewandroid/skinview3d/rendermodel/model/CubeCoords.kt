package dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.model

object CubeCoords {
    val unit = 0.5f

    val cube = floatArrayOf(
        -unit, unit, -unit, /* Back. */
        unit, unit, -unit,
        -unit, -unit, -unit,
        unit, -unit, -unit,
        -unit, unit, unit, /* Front. */
        unit, unit, unit,
        -unit, -unit, unit,
        unit, -unit, unit,
        -unit, unit, -unit, /* Left. */
        -unit, unit, unit,
        -unit, -unit, -unit,
        -unit, -unit, unit,
        unit, unit, unit, /* Right. */
        unit, unit, -unit,
        unit, -unit, unit,
        unit, -unit, -unit,
        -unit, unit, -unit, /* Top. */
        unit, unit, -unit,
        -unit, unit, unit,
        unit, unit, unit,
        -unit, -unit, -unit, /* Bottom. */
        unit, -unit, -unit,
        -unit, -unit, unit,
        unit, -unit, unit,
    )

    val cubeIndicies = shortArrayOf(
        0, 2, 3, 0, 1, 3,
        4, 6, 7, 4, 5, 7,
        8, 10, 11, 8, 9, 11,
        12, 14, 15, 12, 13, 15,
        16, 18, 19, 16, 17, 19,
        20, 22, 23, 20, 21, 23
    )

    fun getSquare(
        multiplyX: Float = 1.0f,
        multiplyY: Float = 1.0f,
        multiplyZ: Float = 1.0f,
        addX: Float = 0.0f,
        addY: Float = 0.0f,
        addZ: Float = 0.0f,
        enlarge: Float = 1.0f,
    ): FloatArray {
        return cube.map {
            it * enlarge
        }.mapIndexed { index, fl ->
            when {
                index % 3 == 0 -> {
                    fl * multiplyX + addX
                }
                index % 3 == 1 -> {
                    fl * multiplyY + addY
                }
                else -> {
                    fl * multiplyZ + addZ
                }
            }
        }.toFloatArray()
    }

    fun getSquareIndicies(offset: Int): ShortArray {
        return cubeIndicies.map {
            (it + offset).toShort()
        }.toShortArray()
    }

    fun getCubeIndicies(cubes: Int = 1): ShortArray {
        val cube = mutableListOf<Short>()
        for (idx in 1..cubes) {
            cube.addAll(getSquareIndicies((idx - 1) * 24).toList())
        }
        return cube.toShortArray()
    }
}