package xyz.laziness.dailycommit.utils.datetime

import java.text.SimpleDateFormat
import java.util.*


class DateUtil {

    companion object {

        fun getWeekDay(calendar: Calendar) =
                calendar.get(Calendar.DAY_OF_WEEK) - 1

        fun isFirstDayOfWeek(calendar: Calendar) =
                calendar.get(Calendar.DAY_OF_WEEK) == 1

        fun isFirstDayOfWeekInMonth(calendar: Calendar) =
                calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1

        fun getShortMonthName(calendar: Calendar, locale: Locale = Locale.US) =
                SimpleDateFormat("MMM", locale).format(calendar.time)

    }

}