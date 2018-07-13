package xyz.laziness.dailycommit.data.database.repository.friends

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "friends",
        indices = [
            (Index(value = arrayOf("friend_name", "user_name"), unique = true))
        ])
data class Friend(

        @PrimaryKey(autoGenerate = true) var id: Long,

        @Expose
        @ColumnInfo(name = "friend_name")
        @SerializedName(value = "friend_name")
        var friendName: String,

        @Expose
        @ColumnInfo(name = "user_name")
        @SerializedName(value = "user_name")
        var userName: String

)