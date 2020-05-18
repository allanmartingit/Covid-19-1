package com.lopes.covidworld

import android.os.Parcel
import android.os.Parcelable

class Countrys(
    var country: String?,
    var cases: Int=0,
    var confirmed: Int=0,
    var deaths: Int=0,
    var recovered: Int=0,
    var data: String?,
    var hora: String?
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(country)
        parcel.writeInt(cases)
        parcel.writeInt(confirmed)
        parcel.writeInt(deaths)
        parcel.writeInt(recovered)
        parcel.writeString(data)
        parcel.writeString(hora)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Countrys> {
        override fun createFromParcel(parcel: Parcel): Countrys {
            return Countrys(parcel)
        }

        override fun newArray(size: Int): Array<Countrys?> {
            return arrayOfNulls(size)
        }
    }
}