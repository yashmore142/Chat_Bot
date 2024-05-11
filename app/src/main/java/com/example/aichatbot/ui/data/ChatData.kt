package com.example.aichatbot.ui.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object  ChatData {
    val api_key = "AIzaSyCvG5utX7H-fiQfpTss_0fwBAKZ8PcXl8Y"

    suspend fun getResponce(prompt : String) : Chat{
        val generativeModel = GenerativeModel(
            modelName ="gemini-pro",
            apiKey = api_key
        )

        try {
            val responce = withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }

            return  Chat(prompt = responce.text ?: "error",
                bitmap = null,
                isFromUser = false)

        }catch (e :Exception){
            return  Chat(prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false)
        }
    }


    suspend fun getResponseWithImage(prompt : String, bitmap: Bitmap) : Chat{
        val generativeModel = GenerativeModel(
            modelName ="gemini-pro-vision",
            apiKey = api_key
        )

        val inputContent = content {
            image(bitmap)
            text(prompt)
        }
        try {
            val responce = withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }

            return  Chat(prompt = responce.text ?: "error",
                bitmap = null,
                isFromUser = false)

        }catch (e :Exception){

            return  Chat(prompt = e.message ?: "error",
                bitmap = null,
                isFromUser = false)
        }
    }
}