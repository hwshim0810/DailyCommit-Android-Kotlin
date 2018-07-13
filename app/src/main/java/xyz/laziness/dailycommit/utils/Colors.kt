package xyz.laziness.dailycommit.utils


object Colors {

    const val DEFAULTS = "DEFAULTS"
    private const val CLASSIC = "CLASSIC"
    private const val OKINAWA_BLUE = "OKINAWA BLUE"
    private const val VELVET = "VELVET"
    private const val BLOOD_ORANGE = "BLOOD ORANGE"
    private const val DEEP_DARK = "DEEP DARK"

    private const val ZERO_COLOR = "#EBEDF0"

    val baseColors = hashMapOf(
            DEFAULTS to listOfNotNull(ZERO_COLOR, "#C6E48B", "#7BC96F", "#239A3B", "#196127"),
            CLASSIC to listOfNotNull(ZERO_COLOR, "#CAC172", "#8F775C", "#DA8D40", "#2D130A"),
            OKINAWA_BLUE to listOfNotNull(ZERO_COLOR, "#7EB2ED", "#4F7BC3", "#384B88", "#1C203C"),
            VELVET to listOfNotNull(ZERO_COLOR, "#EE453F", "#C62D41", "#800E36", "#2A1729"),
            BLOOD_ORANGE to listOfNotNull(ZERO_COLOR, "#C93C16", "#FD3919", "#FE8330", "#7D140B"),
            DEEP_DARK to listOfNotNull(ZERO_COLOR, "#A5A5A5", "#707070", "#414141", "#141414")
    )

}