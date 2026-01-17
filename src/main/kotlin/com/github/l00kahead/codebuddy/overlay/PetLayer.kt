package com.github.l00kahead.codebuddy.overlay

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import java.awt.*
import javax.swing.JComponent
import javax.swing.Timer

class PetLayer(
    private val service: CodeBuddyProjectService
) : JComponent() {

    private val frames = CatFrames.walk
    private var frameIndex = 0

    private var x = 0.0
    private var direction = 1
    private var tick = 0

    private val petSize = 96
    private val groundOffset = 12

    init {
        isOpaque = false
        service.petLayer = this

        Timer(16) {
            if (!service.enabled || !service.running) return@Timer

            tick++

            // Movement speed (stable)
            x += service.speed * direction

            val maxX = width - petSize
            if (x <= 0) {
                x = 0.0
                direction = 1
            } else if (x >= maxX) {
                x = maxX.toDouble()
                direction = -1
            }

            // Animation speed: ~12 FPS
            if (tick % 5 == 0) {
                frameIndex = (frameIndex + 1) % frames.size
            }

            repaint()
        }.start()
    }

    override fun paintComponent(g: Graphics) {
        val g2 = g as Graphics2D
        g2.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR
        )

        val img = frames[frameIndex]
        val y = height - petSize - groundOffset
        val drawX = x.toInt()

        if (direction == 1) {
            g2.drawImage(img, drawX, y, petSize, petSize, null)
        } else {
            g2.drawImage(
                img,
                drawX + petSize,
                y,
                -petSize,
                petSize,
                null
            )
        }
    }
}
