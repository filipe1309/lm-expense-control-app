package com.filipe1309.expensecontrol.network

data class OpenAIPrompt(
    val prompt: String,
    val model: String = "text-davinci-003",
    val maxTokens: Int = 100,
    val n: Int = 1,
    val temperature: Double = 0.7,
)
