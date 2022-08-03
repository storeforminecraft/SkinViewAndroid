package dev.storeforminecraft.skinviewandroid.library.threedimension.model

import dev.storeforminecraft.skinviewandroid.library.datas.ModelSourceTextureType

/**
 * Minecraft Skin Model Coordinates (char.png)
 */
object SteveCoords {
    fun getSteve(modelType: ModelSourceTextureType): Pair<FloatArray, ShortArray> {
        val steveCoords = mutableListOf<Float>()
        val steveIndicies =
            CubeCoords.getCubeIndicies(if (modelType == ModelSourceTextureType.RATIO_1_1) 12 else 7)


        // Head
        CubeCoords.getSquare(addY = CubeCoords.unit * 2.5f).also {
            steveCoords.addAll(it.toList())
        }

        // Torso
        CubeCoords.getSquare(multiplyZ = 0.5f, multiplyY = 1.5f).also {
            steveCoords.addAll(it.toList())
        }

        // Left Arm
        CubeCoords.getSquare(
            multiplyX = 0.5f,
            multiplyZ = 0.5f,
            multiplyY = 1.5f,
            addX = 1.5f * CubeCoords.unit
        ).also {
            steveCoords.addAll(it.toList())
        }

        // Right Arm
        CubeCoords.getSquare(
            multiplyX = 0.5f,
            multiplyZ = 0.5f,
            multiplyY = 1.5f,
            addX = -1.5f * CubeCoords.unit
        ).also {
            steveCoords.addAll(it.toList())
        }

        // Left Leg
        CubeCoords.getSquare(
            multiplyX = 0.5f,
            multiplyZ = 0.5f,
            multiplyY = 1.5f,
            addX = CubeCoords.unit * 0.5f,
            addY = -CubeCoords.unit * 3f
        ).also {
            steveCoords.addAll(it.toList())
        }

        // Right Leg
        CubeCoords.getSquare(
            multiplyX = 0.5f,
            multiplyZ = 0.5f,
            multiplyY = 1.5f,
            addX = -CubeCoords.unit * 0.5f,
            addY = -CubeCoords.unit * 3f
        ).also {
            steveCoords.addAll(it.toList())
        }

        // Hat
        CubeCoords.getSquare(
            addY = CubeCoords.unit * 2.5f,
            enlarge = 1.125f
        ).also {
            steveCoords.addAll(it.toList())
        }

        if (modelType == ModelSourceTextureType.RATIO_1_1) {
            // Torso 2nd layer
            CubeCoords.getSquare(
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                enlarge = 1.125f
            ).also {
                steveCoords.addAll(it.toList())
            }

            // Left Arm 2nd Layer
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = 1.5f * CubeCoords.unit,
                enlarge = 1.125f
            ).also {
                steveCoords.addAll(it.toList())
            }

            // Right Arm 2nd Layer
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = -1.5f * CubeCoords.unit,
                enlarge = 1.125f
            ).also {
                steveCoords.addAll(it.toList())
            }

            // Left Leg 2nd Layer
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = CubeCoords.unit * 0.5f,
                addY = -CubeCoords.unit * 3f,
                enlarge = 1.125f
            ).also {
                steveCoords.addAll(it.toList())
            }

            // Right Leg 2nd Layer
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = -CubeCoords.unit * 0.5f,
                addY = -CubeCoords.unit * 3f,
                enlarge = 1.125f
            ).also {
                steveCoords.addAll(it.toList())
            }
        }

        return Pair(steveCoords.toFloatArray(), steveIndicies)
    }


}