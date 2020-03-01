package com.brtracker.coronavirustrackerapp.util


fun getDateFromUtl(url:String): String {
    val split = url.split("/")
    return split[split.size - 1].split(".")[0]
}