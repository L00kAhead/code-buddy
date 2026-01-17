package com.github.l00kahead.codebuddy.overlay

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager
import javax.swing.JComponent
import com.intellij.openapi.components.service

object PetOverlayManager {

    fun attach(project: Project) {
        val service = project.service<CodeBuddyProjectService>()

        if (service.petLayer != null) return

        val frame = WindowManager.getInstance().getFrame(project) ?: return
        val rootPane = frame.rootPane
        val glassPane = rootPane.glassPane as JComponent

        glassPane.layout = null
        glassPane.isVisible = true
        glassPane.isOpaque = false
        glassPane.isEnabled = false

        val petLayer = PetLayer(service)

        fun resize() {
            val insets = rootPane.insets
            petLayer.setBounds(
                0,
                insets.top,
                rootPane.width,
                rootPane.height - insets.top
            )
        }

        resize()
        glassPane.add(petLayer)
    }
}
