import android.graphics.ColorSpace

class UpdateClockCommand(clock: ModelClock, key: String) : Command {
    private val _key: String = key
    private val _clock: ModelClock = clock

    override fun doIt() {
        when (_key) {
            "addSecond" -> {
                _clock.addSecond()
            }
            "subSecond" -> {
                _clock.subtractSecond()
            }
            "addMinute" -> {
                _clock.addMinute()
            }
            "subMinute" -> {
                _clock.subtractMinute()
            }
            "addHour" -> {
                _clock.addHour()
            }
            "subHour" -> {
                _clock.subtractHour()
            }
            "addDay" -> {
                _clock.addDay()
            }
            "subDay" -> {
                _clock.subtractDay()
            }
            "addMonth" -> {
                _clock.addMonth()
            }
            "subMonth" -> {
                _clock.subtractDay()
            }
            "addYear" -> {
                _clock.addYear()
            }
            "subYear" -> {
                _clock.subtractYear()
            }
        }
    }

    override fun undoIt() {
        when (_key) {
            "addSecond" -> {
                _clock.subtractSecond()
            }
            "subSecond" -> {
                _clock.addSecond()
            }
            "addMinute" -> {
                _clock.subtractMinute()
            }
            "subMinute" -> {
                _clock.addMinute()
            }
            "addHour" -> {
                _clock.subtractHour()
            }
            "subHour" -> {
                _clock.addHour()
            }
            "addDay" -> {
                _clock.subtractDay()
            }
            "subDay" -> {
                _clock.addDay()
            }
            "addMonth" -> {
                _clock.subtractMonth()
            }
            "subMonth" -> {
                _clock.addMonth()
            }
            "addYear" -> {
                _clock.subtractYear()
            }
            "subYear" -> {
                _clock.addYear()
            }
        }
    }
}