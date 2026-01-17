package com.github.l00kahead.codebuddy.lifecycle

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.github.l00kahead.codebuddy.overlay.PetOverlayManager

class CodeBuddyStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        PetOverlayManager.attach(project)
    }
}
