package com.github.linyuzai.cloud.plugin.intellij

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonShortcuts
import com.intellij.ui.JBColor
import com.intellij.ui.SearchTextField
import com.intellij.ui.scale.JBUIScale
import com.intellij.util.ui.JBUI
import java.awt.Dimension
import java.awt.event.KeyEvent
import javax.swing.JComponent

class ConceptLibrariesSearchTextField : SearchTextField() {

    var list: JComponent? = null

    init {
        textEditor.putClientProperty("JTextField.Search.Gap", JBUIScale.scale(6))
        textEditor.putClientProperty("JTextField.Search.GapEmptyText", JBUIScale.scale(-1))
        textEditor.border = JBUI.Borders.empty()
        textEditor.emptyText.text = "Search"

        border = JBUI.Borders.customLine(JBColor.border(), 1, 1, 0, 1)
    }

    override fun preprocessEventForTextField(event: KeyEvent): Boolean {
        val keyCode: Int = event.keyCode
        val id: Int = event.id

        if ((keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_ENTER) && id == KeyEvent.KEY_PRESSED
            && handleListEvents(event)
        ) {
            return true
        }

        return super.preprocessEventForTextField(event)
    }

    private fun handleListEvents(event: KeyEvent): Boolean {
        val selectionTracker = list
        if (selectionTracker != null) {
            selectionTracker.dispatchEvent(event)
            return true
        }
        return false
    }

    override fun getPreferredSize(): Dimension {
        val size = super.getPreferredSize()
        size.height = JBUIScale.scale(30)
        return size
    }

    override fun toClearTextOnEscape(): Boolean {
        object : AnAction() {
            override fun update(e: AnActionEvent) {
                e.presentation.isEnabled = text.isNotEmpty()
            }

            override fun actionPerformed(e: AnActionEvent) {
                text = ""
            }

            init {
                isEnabledInModalContext = true
            }
        }.registerCustomShortcutSet(CommonShortcuts.ESCAPE, this)

        return false
    }
}