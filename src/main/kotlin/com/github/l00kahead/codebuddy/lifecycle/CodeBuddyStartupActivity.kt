package com.github.l00kahead.codebuddy.lifecycle

import com.github.l00kahead.codebuddy.overlay.PetOverlayManager
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

class CodeBuddyStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        ApplicationManager.getApplication().invokeLater {
            PetOverlayManager.attach(project)
        }
    }
}
