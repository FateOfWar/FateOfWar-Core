package me.alex_s168.fow.core.config

import me.alex_s168.ezcfg.i.SerializationRules
import me.alex_s168.fow.core.utils.FeatureSerializer
import me.alex_s168.fow.core.utils.Vec3afSerializer
import me.alex_s168.fow.core.utils.Vec3fSerializer
import me.alex_s168.math.vec.impl.Vec3af
import me.alex_s168.math.vec.impl.Vec3f

class Part {
    /**
     * The rotation of the part (yaw, pitch, roll) in degrees relative to the parent part.
     */
    @SerializationRules<Vec3af>(function = Vec3afSerializer::class)
    lateinit var rotation: Vec3af

    /**
     * The position of the part (x, y, z) in meters relative to the parent part.
     */
    @SerializationRules<Vec3f>(function = Vec3fSerializer::class)
    lateinit var position: Vec3f

    /**
     * The pivot point of the part (x, y, z) in meters relative to the parent part. Used for rotation, scaling, and positioning.
     */
    @SerializationRules<Vec3f>(function = Vec3fSerializer::class)
    lateinit var pivot: Vec3f

    /**
     * The features of the part.
     */
    @SerializationRules<List<Feature>>(function = FeatureSerializer::class)
    lateinit var features: List<Feature>
}