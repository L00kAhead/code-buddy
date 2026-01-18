package com.github.l00kahead.codebuddy

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import com.intellij.openapi.components.service
import com.intellij.testFramework.LightPlatformTestCase

class CodeBuddyProjectServiceTest : LightPlatformTestCase() {

    fun `test service is created`() {
        val service = project.service<CodeBuddyProjectService>()
        assertNotNull(service)
    }

    fun `test default state`() {
        val service = project.service<CodeBuddyProjectService>()

        assertTrue(service.enabled)
        assertTrue(service.running)
        assertTrue(service.speed > 0)
    }

    fun `test toggle running`() {
        val service = project.service<CodeBuddyProjectService>()

        service.toggleRunning()
        assertFalse(service.running)

        service.toggleRunning()
        assertTrue(service.running)
    }

    fun `test toggle enabled`() {
        val service = project.service<CodeBuddyProjectService>()

        service.toggleEnabled()
        assertFalse(service.enabled)

        service.toggleEnabled()
        assertTrue(service.enabled)
    }
}
