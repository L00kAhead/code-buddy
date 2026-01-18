package com.github.l00kahead.codebuddy.core

import com.intellij.openapi.components.Service
import com.github.l00kahead.codebuddy.overlay.PetLayer

@Service(Service.Level.PROJECT)
class CodeBuddyProjectService {

    var enabled: Boolean = true
        set(value) {
            field = value
            petLayer?.isVisible = value
        }

    var running: Boolean = true

    // Tuned walking speed
    var speed: Double = 0.6

    var petLayer: PetLayer? = null

    fun toggleRunning() {
        running = !running
    }

    fun toggleEnabled() {
        enabled = !enabled
    }
}
