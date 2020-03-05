package com.brtracker.coronavirustrackerapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ArticleModel {
    @SerializedName("source")
    @Expose
    var source: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleModel

        if (source != other.source) return false
        if (author != other.author) return false
        if (url != other.url) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (urlToImage != other.urlToImage) return false
        if (publishedAt != other.publishedAt) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source?.hashCode() ?: 0
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (urlToImage?.hashCode() ?: 0)
        result = 31 * result + (publishedAt?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        return result
    }


}