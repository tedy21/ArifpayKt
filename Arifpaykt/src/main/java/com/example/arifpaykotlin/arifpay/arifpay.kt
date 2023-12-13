package com.example.arifpaykotlin.arifpay
import com.example.arifpaykotlin.arifpay.arifValidator
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.Headers.Companion.toHeaders
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.*

class ArifPay(private val apiKey: String) {
    private val headers: MutableMap<String, String> = mutableMapOf()

    companion object {
        const val baseUrl = "https://gateway.arifpay.org/api/sandbox/checkout/session"
    }

    init {
        headers["Content-Type"] = "application/json"   //the type of data being sent in the request of http...it's json format
        headers["x-arifpay-key"] = apiKey
    }

    fun initializePayment(arifCheckoutModel: ArifCheckoutModel): String {
        try {
            val validationErrors = arifValidator.validateKeys(arifCheckoutModel)
            if (validationErrors.isNotEmpty()) {

                return "Invalid keys = ${validationErrors.joinToString { "${it.field}: ${it.message}" }}"
            }

            arifCheckoutModel.nonce = UUID.randomUUID().toString()
            val gson = Gson()
            val json = gson.toJson(arifCheckoutModel)
            val jsonObject = JsonParser.parseString(json) as JsonObject
            val requestBody = jsonObject.toString().toRequestBody("application/json".toMediaType())
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(baseUrl)
                .headers(headers.toHeaders())
                .post(requestBody)
                .build()
            val response = client.newCall(request).execute()
            val responseCode = response.code
            val responseBody = response.body?.string()
            if (response.isSuccessful) {
                return responseBody ?: ""
            } else if (responseCode == 400) {
                return "Validation Error: $responseBody"
            } else {
                return "Request failed with status: $responseCode"
            }
        } catch (e: Exception) {
            return "Error occurred: $e"
        }
    }
}
