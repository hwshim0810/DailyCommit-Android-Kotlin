package xyz.laziness.dailycommit.data.parser

import org.jsoup.nodes.Element
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.utils.Colors
import java.text.SimpleDateFormat
import java.util.*


class ContributionParser {

    companion object {

        private const val fillAttr = "fill"
        private const val countAttr = "data-count"
        private const val dateAttr = "data-date"
        private const val dateFormat = "yyyy-MM-dd"
        private val defaultColors = Colors.baseColors[Colors.DEFAULTS]
        private var baseBlockColor: List<String>? = null

        const val targetElement = "rect.day"

        private fun getColor(element: Element): String {
            baseBlockColor = baseBlockColor ?: defaultColors
            return when (element.attr(fillAttr).toUpperCase()) {
                defaultColors?.get(0) ?: "#EBEDF0" -> baseBlockColor!![0]
                defaultColors?.get(1) ?: "#239A3B" -> baseBlockColor!![1]
                defaultColors?.get(2) ?: "#C6E48B" -> baseBlockColor!![2]
                defaultColors?.get(3) ?: "#7BC96F" -> baseBlockColor!![3]
                defaultColors?.get(4) ?: "#196127" -> baseBlockColor!![4]
                else -> baseBlockColor!![0]
            }
        }

        private fun getCount(element: Element): Int =
                element.attr(countAttr).toIntOrNull() ?: 0

        private fun getCalendar(element: Element): Calendar =
                Calendar.getInstance().apply {
                    time = SimpleDateFormat(dateFormat, Locale.US).parse(element.attr(dateAttr))
                    firstDayOfWeek = Calendar.SUNDAY
                }

        fun setBaseBlockColor(baseBlockColor: List<String>?) {
            this.baseBlockColor = baseBlockColor
        }

        fun hasAttributes(element: Element): Boolean =
                element.hasAttr(fillAttr) &&
                        element.hasAttr(countAttr) &&
                        element.hasAttr(dateAttr)

        fun parse(element: Element): ContributionDay =
                element.run {
                    ContributionDay(color = getColor(this),
                            count = getCount(this),
                            date = getCalendar(this))
                }


    }

}