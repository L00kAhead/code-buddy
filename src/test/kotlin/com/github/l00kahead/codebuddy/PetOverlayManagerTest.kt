package com.github.l00kahead.codebuddy

import com.github.l00kahead.codebuddy.overlay.PetOverlayManager
import com.intellij.testFramework.LightPlatformTestCase

class PetOverlayManagerTest : LightPlatformTestCase() {

    fun testAttachAndDetachDoNotCrash() {
        // These should not throw exceptions
        PetOverlayManager.attach(project)
        PetOverlayManager.detach(project)
    }
}
