package com.github.l00kahead.codebuddy.toolWindow

import com.github.l00kahead.codebuddy.core.CodeBuddyProjectService
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.BottomGap
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.panel
import com.intellij.util.ui.JBUI
import java.awt.Font
import javax.swing.JButton
import javax.swing.JLabel

class CodeBuddyToolWindowFactory : ToolWindowFactory {

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val service = project.getService(CodeBuddyProjectService::class.java)

        lateinit var statusLabel: Cell<JLabel>
        lateinit var runButton: Cell<JButton>
        lateinit var enableButton: Cell<JButton>

        val root = panel {
            row {
                label("Code Buddy").applyToComponent {
                    font = font.deriveFont(Font.BOLD)
                }
            }

            row {
                statusLabel = label("")
            }.bottomGap(BottomGap.MEDIUM)

            row {
                runButton = button("") {
                    service.toggleRunning()
                    refresh(service, statusLabel, runButton, enableButton)
                }.align(AlignX.FILL)
            }

            row {
                enableButton = button("") {
                    service.toggleEnabled()
                    if (!service.enabled) {
                        service.running = false
                    }
                    refresh(service, statusLabel, runButton, enableButton)
                }.align(AlignX.FILL)
            }
        }.apply {
            border = JBUI.Borders.empty(12)
        }

        val content = toolWindow.contentManager.factory
            .createContent(root, null, false)

        toolWindow.contentManager.addContent(content)

        refresh(service, statusLabel, runButton, enableButton)
    }

    private fun refresh(
        service: CodeBuddyProjectService,
        status: Cell<JLabel>,
        runButton: Cell<JButton>,
        enableButton: Cell<JButton>
    ) {
        when {
            !service.enabled -> {
                status.component.text = "Disabled"

                runButton.component.isEnabled = false
                runButton.component.text = "Pause"

                enableButton.component.text = "Enable"
            }

            service.running -> {
                status.component.text = "Running"

                runButton.component.isEnabled = true
                runButton.component.text = "Pause"

                enableButton.component.text = "Disable"
            }

            else -> {
                status.component.text = "Paused"

                runButton.component.isEnabled = true
                runButton.component.text = "Resume"

                enableButton.component.text = "Disable"
            }
        }

        runButton.component.preferredSize =
            JBUI.size(runButton.component.preferredSize.width, 32)

        enableButton.component.preferredSize =
            JBUI.size(enableButton.component.preferredSize.width, 32)

        runButton.component.maximumSize =
            JBUI.size(Int.MAX_VALUE, 32)

        enableButton.component.maximumSize =
            JBUI.size(Int.MAX_VALUE, 32)
    }
}
