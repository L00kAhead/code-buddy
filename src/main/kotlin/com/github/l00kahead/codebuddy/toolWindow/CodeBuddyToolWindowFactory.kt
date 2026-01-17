package com.github.l00kahead.codebuddy.toolWindow

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class CodeBuddyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val service = project.getService(CodeBuddyProjectService::class.java)

        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val title = JLabel("Code Buddy 🐱")

        // Button 1: Walk / Pause
        val walkToggle = JButton()
        fun updateWalkText() {
            walkToggle.text = if (service.running) "Pause ⏸" else "Resume ▶"
        }
        updateWalkText()

        walkToggle.addActionListener {
            service.toggleRunning()
            updateWalkText()
        }

        // Button 2: Enable / Disable
        val enableToggle = JButton()
        fun updateEnableText() {
            enableToggle.text = if (service.enabled) "Disable Cat" else "Enable Cat"
        }
        updateEnableText()

        enableToggle.addActionListener {
            service.toggleEnabled()
            updateEnableText()
        }

        panel.add(title)
        panel.add(Box.createVerticalStrut(12))
        panel.add(walkToggle)
        panel.add(Box.createVerticalStrut(8))
        panel.add(enableToggle)

        val content = toolWindow.contentManager.factory
            .createContent(panel, null, false)

        toolWindow.contentManager.addContent(content)
    }
}
