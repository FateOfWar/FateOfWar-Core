package me.alex_s168.fow.core.config

import me.alex_s168.math.Anglef
import me.alex_s168.math.vec.impl.Vec3af
import me.alex_s168.math.vec.impl.Vec3f

abstract class Feature(
    val position: Vec3f,
    val rotation: Vec3af
) {
    class Camera(position: Vec3f, rotation: Vec3af, val fov: Anglef, val destination: String):
        Feature(position, rotation)

    class Light(position: Vec3f, rotation: Vec3af, val color: Int, val intensity: Float):
        Feature(position, rotation)

    class Seat(position: Vec3f, rotation: Vec3af, val shrinking: Float):
        Feature(position, rotation)

    class Button(position: Vec3f, rotation: Vec3af, val action: String, val isToggle: Boolean):
        Feature(position, rotation)

    class Display(position: Vec3f, rotation: Vec3af, val width: Float, val height: Float, val source: String):
        Feature(position, rotation)

    class Speaker(position: Vec3f, rotation: Vec3af, val source: String, val volume: Float, val gain: Float, val noise: Float):
        Feature(position, rotation)

    class Microphone(position: Vec3f, rotation: Vec3af, val destination: String, val sensitivity: Float, val gain: Float, val noise: Float, val volume: Float, val echo: Float):
        Feature(position, rotation)

    class CondLight(position: Vec3f, rotation: Vec3af, val color: Int, val intensity: Float, val action: String):
        Feature(position, rotation)

    companion object {
        val NONE = object : Feature(Vec3f(), Vec3af()) {}
    }
}