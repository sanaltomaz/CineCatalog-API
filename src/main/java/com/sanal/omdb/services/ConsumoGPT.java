package com.sanal.omdb.services;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class ConsumoGPT {
    public static String obterTraducao(String texto) {

        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .model("gpt-5-nano")
                .addUserMessage("Traduza para o português: " + texto)
                .build();

        ChatCompletion chatCompletion = client.chat() 
                .completions()
                .create(params);

        return chatCompletion.choices()
                .get(0)
                .message()
                .content()
                .orElse("");
    }
}

