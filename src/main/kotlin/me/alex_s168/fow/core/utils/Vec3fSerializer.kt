package me.alex_s168.fow.core.utils

import me.alex_s168.ezcfg.ErrorContext
import me.alex_s168.ezcfg.i.Element
import me.alex_s168.ezcfg.i.SerializationFunction
import me.alex_s168.ezcfg.i.apply
import me.alex_s168.math.vec.impl.Vec3f

class Vec3fSerializer: SerializationFunction<Vec3f> {
    override fun deserialize(element: Element, errorContext: ErrorContext): Vec3f {
        val arr = element.apply(floatArrayOf(), errorContext)
        return Vec3f.wrap(arr)
    }
}