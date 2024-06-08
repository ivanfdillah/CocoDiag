package com.dicoding.capstone.cocodiag.data.remote

import com.dicoding.capstone.cocodiag.data.remote.payload.ClassificationResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.CreateUserParam
import com.dicoding.capstone.cocodiag.data.remote.payload.SignInParam
import com.dicoding.capstone.cocodiag.data.remote.payload.SignInResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.SignUpResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("signup")
    suspend fun createUser(@Body param: CreateUserParam): SignUpResponse

    @POST("signin")
    suspend fun auth(@Body param: SignInParam): SignInResponse

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part fileImage: MultipartBody.Part
    ): ClassificationResponse
}