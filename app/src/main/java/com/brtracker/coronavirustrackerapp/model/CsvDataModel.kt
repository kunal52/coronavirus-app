package com.brtracker.coronavirustrackerapp.model

class CsvDataModel {
    lateinit var state: String
    lateinit var country: String
    lateinit var lastUpdate: String
    lateinit var confirmed: String
    lateinit var deaths: String
    lateinit var recovered: String
    override fun toString(): String {
        return "CsvDataModel(state='$state', country='$country', lastUpdate='$lastUpdate', confirmed='$confirmed', deaths='$deaths', recovered='$recovered')"
    }


}