package com.dicoding.capstone.cocodiag.data.remote

import com.dicoding.capstone.cocodiag.data.local.model.ArticleModel
import com.dicoding.capstone.cocodiag.data.remote.payload.ClassificationResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.CreateUserParam
import com.dicoding.capstone.cocodiag.data.remote.payload.PasswordUserResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.SignInParam
import com.dicoding.capstone.cocodiag.data.remote.payload.SignInResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.SignUpResponse
import com.dicoding.capstone.cocodiag.data.remote.payload.UpdatePasswordParam
import com.dicoding.capstone.cocodiag.data.remote.payload.UpdateUserParam
import com.dicoding.capstone.cocodiag.data.remote.payload.UserResponse
import com.dicoding.capstone.cocodiag.features.price.PriceItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @POST("signup")
    suspend fun createUser(@Body param: CreateUserParam): SignUpResponse

    @POST("signin")
    suspend fun auth(@Body param: SignInParam): SignInResponse

    @GET("/user/{user_id}")
    suspend fun findUserById(@Path("user_id") userId: String): UserResponse

    @PUT("/user")
    suspend fun updateUser(@Body param: UpdateUserParam): UserResponse

    @PUT("/user")
    suspend fun updatePassword(@Body param: UpdatePasswordParam): PasswordUserResponse

    @Multipart
    @POST("predict")
    suspend fun predict(
        @Part imageFile: MultipartBody.Part,
        @Part("user_id") userId: RequestBody
    ): ClassificationResponse

    @GET("getNews")
    suspend fun getNews(): List<ArticleModel>

    @GET("getPrice")
    suspend fun getPrice():PriceItem
}