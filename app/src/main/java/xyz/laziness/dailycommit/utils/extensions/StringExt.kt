package xyz.laziness.dailycommit.utils.extensions

import java.util.regex.Pattern


fun String.validate(regex: Pattern) = regex.matcher(this).find()