package com.example.musicstream.models

data class SongModel(
    val id : String,
    val title : String,
    val subtitle : String,
    val cover : String,
    val url : String
){
    constructor() : this(id= "",title= "", subtitle= "",cover= "", url = "")
}
