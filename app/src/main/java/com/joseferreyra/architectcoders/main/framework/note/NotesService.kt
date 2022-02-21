package com.joseferreyra.architectcoders.main.framework.note

import retrofit2.Call
import retrofit2.http.GET

interface NotesService {

    //https://private-e7c3d8-classifiednotes.apiary-mock.com/
    @GET("https://private-e7c3d8-classifiednotes.apiary-mock.com/")
    fun requestNotes(): Call<NotesResponse>

}