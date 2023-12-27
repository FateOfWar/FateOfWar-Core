package me.alex_s168.fow.core.utils

import me.alex_s168.ezcfg.ErrorContext
import me.alex_s168.ezcfg.addError
import me.alex_s168.ezcfg.i.Element
import me.alex_s168.ezcfg.i.apply
import me.alex_s168.ezcfg.i.SerializationFunction
import me.alex_s168.ezcfg.i.SerializationRules
import me.alex_s168.fow.core.config.Feature
import me.alex_s168.math.Anglef
import me.alex_s168.math.vec.impl.Vec3af
import me.alex_s168.math.vec.impl.Vec3f

class FeatureSerializer: SerializationFunction<List<Feature>> {
    class TempData {
        /**
         * The position of the feature (x, y, z) in meters relative to the parent part.
         */
        @SerializationRules<Vec3f>(function = Vec3fSerializer::class)
        lateinit var position: Vec3f

        /**
         * The rotation of the feature (yaw, pitch, roll) in degrees relative to the parent part.
         */
        @SerializationRules<Vec3af>(function = Vec3afSerializer::class)
        lateinit var rotation: Vec3af

        /**
         * The size of the feature (x, y, z) in meters.
         */
        @SerializationRules<Vec3f>(function = Vec3fSerializer::class)
        lateinit var size: Vec3f

        /**
         * The type of the feature. Available: "camera", "light", "seat", "button", "display", "speaker", "microphone", "cond_light".
         */
        lateinit var type: String

        /**
         * (for: camera) The field of view of the camera in degrees.
         */
        var fov: Float = 0f

        /**
         * (for: camera, microphone) The destination buffer for the camera's video feed / microphone's audio feed.
         */
        lateinit var destination: String

        /**
         * (for: light) The color of the light in hex.
         */
        var color: Int = 0

        /**
         * (for: light, cond_light) The intensity of the light.
         */
        var intensity: Float = 0f

        /**
         * (for: seat) The shrinking factor of the seat.
         */
        var shrinking: Float = 0f

        /**
         * (for: button, cond_light) The action to perform when the button is pressed / when to turn on the light.
         */
        lateinit var action: String

        /**
         * (for button) The mode of the button. Available: "toggle", "push".
         */
        lateinit var mode: String

        /**
         * (for: display) The width of the display in meters.
         */
        var width: Float = 0f

        /**
         * (for: display) The height of the display in meters.
         */
        var height: Float = 0f

        /**
         * (for: display, speaker) The source buffer for the display's video feed / speaker's audio feed.
         */
        lateinit var source: String

        /**
         * (for: speaker, microphone) The volume of the speaker / microphone.
         */
        var volume: Float = 0f

        /**
         * (for: speaker, microphone) The gain of the speaker / microphone.
         */
        var gain: Float = 0f

        /**
         * (for: speaker, microphone) The noise of the speaker / microphone.
         */
        var noise: Float = 0f

        /**
         * (for: microphone) The sensitivity of the microphone.
         */
        var sensitivity: Float = 0f

        /**
         * (for: microphone) The echo of the microphone.
         */
        var echo: Float = 0f
    }

    override fun deserialize(element: Element, errorContext: ErrorContext): List<Feature> =
        element.apply(arrayOf<TempData>(), errorContext).map {
            try {
                when (it.type) {
                    "camera" -> Feature.Camera(it.position, it.rotation, Anglef.fromDegrees(it.fov), it.destination)
                    "light" -> Feature.Light(it.position, it.rotation, it.color, it.intensity)
                    "seat" -> Feature.Seat(it.position, it.rotation, it.shrinking)
                    "button" -> Feature.Button(it.position, it.rotation, it.action, it.mode == "toggle")
                    "display" -> Feature.Display(it.position, it.rotation, it.width, it.height, it.source)
                    "speaker" -> Feature.Speaker(it.position, it.rotation, it.source, it.volume, it.gain, it.noise)
                    "microphone" -> Feature.Microphone(it.position, it.rotation, it.destination, it.sensitivity, it.gain, it.noise, it.volume, it.echo)
                    "cond_light" -> Feature.CondLight(it.position, it.rotation, it.color, it.intensity, it.action)
                    else -> {
                        errorContext.addError(element, "Unknown feature type: ${it.type}")
                        Feature.NONE
                    }
                }
            } catch (e: Exception) {
                errorContext.addError(element, "One or more required fields are missing for feature type: ${it.type}")
                Feature.NONE
            }
        }
}