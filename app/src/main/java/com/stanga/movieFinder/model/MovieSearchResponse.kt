package com.stanga.movieFinder.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("totalResults")
    val totalResults: String,

    @SerializedName("Response")
    val response: String,

    @SerializedName("Search")
    val movies: List<Movie>
)

class Movie() : Parcelable {
    @SerializedName("Title")
    var title: String = ""

    @SerializedName("Year")
    var year: String = ""

    @SerializedName("imdbID")
    var imdbID: String = ""

    @SerializedName("Type")
    var type: String = ""

    @SerializedName("Poster")
    var poster: String = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString() ?: ""
        year = parcel.readString() ?: ""
        imdbID = parcel.readString() ?: ""
        type = parcel.readString() ?: ""
        poster = parcel.readString() ?: ""
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(imdbID)
        parcel.writeString(type)
        parcel.writeString(poster)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}