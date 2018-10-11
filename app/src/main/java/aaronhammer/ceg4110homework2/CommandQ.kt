package aaronhammer.ceg4110homework2

import java.util.*
import UpdateClockCommand

class CommandQ {
    companion object Factory {
        fun create(): CommandQ = CommandQ()
    }

    private var _cmdDone = Stack<UpdateClockCommand>()
    private var _cmdUndone = Stack<UpdateClockCommand>()

    fun push(command: UpdateClockCommand) {
        _cmdDone.push(command)
    }

    fun undo() {
        if (!_cmdDone.empty()) {
            val command = _cmdDone.pop()
            command.undoIt()
            _cmdUndone.push(command)
        }
    }

    fun redo() {
        if (!_cmdUndone.empty()) {
            val command = _cmdUndone.pop()
            command.doIt()
            _cmdDone.push(command)
        }
    }
}