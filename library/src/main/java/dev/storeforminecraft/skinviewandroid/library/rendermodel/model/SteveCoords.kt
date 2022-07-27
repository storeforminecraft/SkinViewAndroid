package dev.storeforminecraft.skinviewandroid.library.rendermodel.model

object SteveCoords {
    fun getSteveUV(): FloatArray {
        return SteveTextures.getHeadTex() +
                SteveTextures.getTorsoTex(16f, 16f) +
                SteveTextures.getLegArmTex(32f, 48f, false) +
                SteveTextures.getLegArmTex(40f, 16f, true) +
                SteveTextures.getLegArmTex(16f, 48f, false) +
                SteveTextures.getLegArmTex(0f, 16f, true) +
                SteveTextures.getHeadTex(32f, 0f) +
                SteveTextures.getTorsoTex(16f, 32f) +
                SteveTextures.getLegArmTex(48f, 48f, false) +
                SteveTextures.getLegArmTex(40f, 32f, true) +
                SteveTextures.getLegArmTex(0f, 48f, false) +
                SteveTextures.getLegArmTex(0f, 32f, true)
    }

    fun getSteve(): Pair<FloatArray, ShortArray> {
        val steveCoords = mutableListOf<Float>()
        val steveIndicies = CubeCoords.getCubeIndicies(12)

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

        return Pair(steveCoords.toFloatArray(), steveIndicies)
    }


}