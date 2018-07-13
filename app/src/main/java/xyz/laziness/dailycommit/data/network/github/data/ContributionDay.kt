package xyz.laziness.dailycommit.data.network.github.data

import java.util.*


data class ContributionDay(

        var color: String,

        var count: Int,

        var date: Calendar

) {
    override fun toString(): String {
        return "ContributionDay(color=$color, count=$count, date=$date)"
    }
}