package com.brtracker.coronavirustrackerapp.util


fun getDateFromUrl(url: String): String {
    val split = url.split("/")
    return split[split.size - 1].split(".")[0]
}

fun getFileNameFromUrl(url: String): String {
    return getDateFromUrl(url) + ".csv";
}


fun createDownloadedUrlFromFileName(fileName: String): String {
    return "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports/$fileName";
}