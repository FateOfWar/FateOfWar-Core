package me.alex_s168.fow.core.utils

import me.alex_s168.ezcfg.ErrorContext
import me.alex_s168.ezcfg.i.Element
import me.alex_s168.ezcfg.i.SerializationFunction
import me.alex_s168.ezcfg.i.apply
import me.alex_s168.math.vec.impl.Vec3af
import me.alex_s168.math.vec.impl.Vec3f

class Vec3afSerializer: SerializationFunction<Vec3af> {
    override fun deserialize(element: Element, errorContext: ErrorContext): Vec3af {
        val arr = element.apply(floatArrayOf(), errorContext)
        return Vec3af.fromDegrees(Vec3f.wrap(arr))
    }
}