import android.os.Handler
import java.io.Serializable
import java.util.*


class ModelClock(): Serializable {
    var calender = GregorianCalendar()
    var second = 0
    var minute = 0
    var hour = 0
    var day = 0
    var month = 0
    var year = 0

    //runs without a timer by reposting this handler at the end of the runnable
    var timerHandler = Handler()
    var timerRunnable: Runnable = object : Runnable {
        override fun run() {
            var cal = GregorianCalendar.getInstance()
            calender.set(GregorianCalendar.SECOND, cal.get(GregorianCalendar.SECOND) + second)
            calender.set(GregorianCalendar.MINUTE, cal.get(GregorianCalendar.MINUTE) + minute)
            calender.set(GregorianCalendar.HOUR_OF_DAY, cal.get(GregorianCalendar.HOUR_OF_DAY) + hour)
            calender.set(GregorianCalendar.DAY_OF_MONTH, cal.get(GregorianCalendar.DAY_OF_MONTH) + day)
            calender.set(GregorianCalendar.MONTH, cal.get(GregorianCalendar.MONTH) + month)
            calender.set(GregorianCalendar.YEAR, cal.get(GregorianCalendar.YEAR) + year)
            //Log.d("Time", calender.time.toString())
            timerHandler.postDelayed(this, 100)
        }
    }

    init {
        timerHandler.post(timerRunnable)
    }

    fun destroy() {
        timerHandler.removeCallbacks(timerRunnable)
    }

    fun addSecond() {
        second += 1
    }

    fun subtractSecond() {
        second -= 1
    }

    fun addMinute() {
        minute += 1
    }

    fun subtractMinute() {
        minute -= 1
    }

    fun addHour() {
        hour += 1
    }

    fun subtractHour() {
        hour -= 1
    }

    fun addDay() {
        day += 1
    }

    fun subtractDay() {
        day -= 1
    }

    fun addMonth() {
        month += 1
    }

    fun subtractMonth() {
        month -= 1
    }

    fun addYear() {
        year += 1
    }

    fun subtractYear() {
        year -= 1
    }
}