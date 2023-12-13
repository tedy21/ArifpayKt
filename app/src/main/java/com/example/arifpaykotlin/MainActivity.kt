package com.example.arifpaykotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.util.Log
import com.example.arifpaykotlin.arifpay.ArifBeneficiary
import com.example.arifpaykotlin.arifpay.ArifCheckoutItem
import com.example.arifpaykotlin.arifpay.ArifCheckoutModel
import com.example.arifpaykotlin.arifpay.ArifPay
import com.example.arifpaykotlin.arifpay.arifValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set your Compose UI content here if needed
        }

        val arifPay = ArifPay("tZzd6Kd34xXfY7GNAi9eMjjLeaNXuxYR")
        val checkoutModel = ArifCheckoutModel(
            cancelUrl = "https://example.com",
            phone = "251944294981",
            email = "natnael@arifpay.net",
            errorUrl = "http://error.com",
            notifyUrl = "https://gateway.arifpay.net/test/callback",
            successUrl = "http://example.com",
            paymentMethods = listOf("TELEBIRR"),
            expireDate = "2025-02-01T03:45:27",
            items = listOf(
                ArifCheckoutItem(
                    name = "ሙዝ",
                    quantity = 1,
                    price = 1,
                    description = "Fresh Corner premium Banana.",
                    image = "https://4.imimg.com/data4/KK/KK/GLADMIN-/product-8789_bananas_golden-500x500.jpg"
                ),
                ArifCheckoutItem(
                    name = "ሙዝ",
                    quantity = 3,
                    price = 1,
                    description = "Fresh Corner premium Banana.",
                    image = ""
                )
            ),
            beneficiaries = listOf(
                ArifBeneficiary(
                    accountNumber = "01320811436100",
                    bank = "AWINETAA",
                    amount = 4.0
                )
            ),
            lang = "EN"
        )
        Log.d("MainActivity", "Initializing payment")

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val responseUrl = arifPay.initializePayment(checkoutModel)
                withContext(Dispatchers.Main) {
                    Log.d("MainActivity", "Response: $responseUrl")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("MainActivity", "Error occurred: $e")
                }
            }
        }
    }
}