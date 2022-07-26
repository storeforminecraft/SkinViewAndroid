package dev.storeforminecraft.skinviewandroid.skinview3d.rendermodel.model

object SteveCoords {
    fun getSteveUV(): FloatArray {
        return SteveTextures.getHeadTex() +
                SteveTextures.getTorsoTex(16f, 16f) +
                SteveTextures.getLegArmTex(32f, 48f, false) +
                SteveTextures.getLegArmTex(40f, 16f, true) +
                SteveTextures.getLegArmTex(16f, 48f, false) +
                SteveTextures.getLegArmTex(0f, 16f, true)+
                SteveTextures.getHeadTex(32f, 0f)
    }

    fun getSteve(): Pair<FloatArray, ShortArray> {
        val steveCoords = mutableListOf<Float>()
        val steveIndicies = mutableListOf<Short>()

        val head = CubeCoords.getSquare(addY = CubeCoords.unit * 2.5f)
        steveCoords.addAll(head.toList())
        val headIndicies = CubeCoords.getSquareIndicies(0)
        steveIndicies.addAll(headIndicies.toList())

        val torso = CubeCoords.getSquare(multiplyZ = 0.5f, multiplyY = 1.5f)
        steveCoords.addAll(torso.toList())
        val torsoIndicies = CubeCoords.getSquareIndicies(24)
        steveIndicies.addAll(torsoIndicies.toList())

        val leftArm =
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = -1.5f * CubeCoords.unit
            )
        steveCoords.addAll(leftArm.toList())
        val leftArmIndicies = CubeCoords.getSquareIndicies(48)
        steveIndicies.addAll(leftArmIndicies.toList())

        val rightArm =
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = 1.5f * CubeCoords.unit
            )
        steveCoords.addAll(rightArm.toList())
        val rightArmIndicies = CubeCoords.getSquareIndicies(72)
        steveIndicies.addAll(rightArmIndicies.toList())

        val leftLeg =
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = -CubeCoords.unit * 0.5f,
                addY = -CubeCoords.unit * 3f
            )
        steveCoords.addAll(leftLeg.toList())
        val leftLegIndicies = CubeCoords.getSquareIndicies(96)
        steveIndicies.addAll(leftLegIndicies.toList())

        val rightLeg =
            CubeCoords.getSquare(
                multiplyX = 0.5f,
                multiplyZ = 0.5f,
                multiplyY = 1.5f,
                addX = 1 * CubeCoords.unit * 0.5f,
                addY = -CubeCoords.unit * 3f
            )
        steveCoords.addAll(rightLeg.toList())
        val rightLegIndicies = CubeCoords.getSquareIndicies(120)
        steveIndicies.addAll(rightLegIndicies.toList())

        // 0.625 y add
        val head2 = CubeCoords.getSquare(
            1.125f, 1.125f, 1.125f,
            0f, CubeCoords.unit * 2.5f, 0f
        )
        steveCoords.addAll(head2.toList())
        val headIndicies2 = CubeCoords.getSquareIndicies(144)
        steveIndicies.addAll(headIndicies2.toList())

        return Pair(steveCoords.toFloatArray(), steveIndicies.toShortArray())
    }


}