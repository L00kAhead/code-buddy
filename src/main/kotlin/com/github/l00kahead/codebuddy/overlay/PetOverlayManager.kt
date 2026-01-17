package com.github.l00kahead.codebuddy.overlay

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.WindowManager
import javax.swing.JLayeredPane

object PetOverlayManager {

    fun attach(project: Project) {
        val service = project.service<CodeBuddyProjectService>()
        if (service.petLayer != null) return

        val frame = WindowManager.getInstance().getFrame(project) ?: return
        val layeredPane = frame.rootPane.layeredPane

        val petLayer = PetLayer(service)
        layeredPane.add(petLayer, JLayeredPane.POPUP_LAYER)
        petLayer.attach(layeredPane)
    }
    @Suppress("unused")
    fun detach(project: Project) {
        val service = project.service<CodeBuddyProjectService>()
        val petLayer = service.petLayer ?: return

        val parent = petLayer.parent
        parent?.remove(petLayer)
        parent?.repaint()

        service.petLayer = null
    }
}
