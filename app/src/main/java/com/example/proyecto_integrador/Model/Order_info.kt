package com.example.proyecto_integrador.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class Order_info(val titulo:String, val precio:Double, val imagen:Int, val descripcion:String):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readDouble()!!,
        parcel.readInt()!!,
        parcel.readString()!!
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeDouble(precio)
        parcel.writeInt(imagen)
        parcel.writeString(descripcion)

    }
    companion object CREATOR : Parcelable.Creator<Order_info> {
        override fun createFromParcel(parcel: Parcel): Order_info {
            return Order_info(parcel)
        }

        override fun newArray(size: Int): Array<Order_info?> {
            return arrayOfNulls(size)
        }
    }
}