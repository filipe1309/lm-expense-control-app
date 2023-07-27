package com.filipe1309.expensecontrol.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAIService {
    @POST("v1/completions")
    suspend fun completitions(@Body prompt: OpenAIPrompt): Response<OpenAIResponse>
}