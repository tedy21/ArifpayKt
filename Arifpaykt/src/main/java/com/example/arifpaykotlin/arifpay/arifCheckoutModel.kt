package com.example.arifpaykotlin.arifpay
import java.util.UUID

class ArifCheckoutModel(
    var cancelUrl: String,
    var phone: String,
    var email: String,
    var errorUrl: String,
    var notifyUrl: String,
    var successUrl: String,
    var paymentMethods: List<String>,
    var expireDate: String,
    var items: List<ArifCheckoutItem>,
    var beneficiaries: List<ArifBeneficiary>,
    var lang: String
) {
    var nonce: String = ""

    init {
        generateNonce()
    }

    fun generateNonce() {
        nonce = UUID.randomUUID().toString()
    }

    fun toJson(): Map<String, Any?> {
        return mapOf(
            "cancelUrl" to cancelUrl,
            "nonce" to nonce,
            "phone" to phone,
            "email" to email,
            "errorUrl" to errorUrl,
            "notifyUrl" to notifyUrl,
            "successUrl" to successUrl,
            "paymentMethods" to paymentMethods,
            "expireDate" to expireDate,
            "items" to items.map { it.toJson() },
            "beneficiaries" to beneficiaries.map { it.toJson() },
            "lang" to lang
        )
    }
}

class ArifCheckoutItem(
    var name: String,
    var price: Int,
    var quantity: Int,
    var description: String,
    var image: String
) {
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "price" to price,
            "quantity" to quantity,
            "description" to description,
            "image" to image
        )
    }
}

class ArifBeneficiary(
    var accountNumber: String,
    var bank: String,
    var amount: Double
) {
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "accountNumber" to accountNumber,
            "bank" to bank,
            "amount" to amount
        )
    }
}