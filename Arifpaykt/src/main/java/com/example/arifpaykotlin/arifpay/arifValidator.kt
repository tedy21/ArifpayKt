package com.example.arifpaykotlin.arifpay

class arifValidator {
    companion object {
        data class ValidationError(val field: String, val message: String)

        fun validateKeys(checkout: ArifCheckoutModel): List<ValidationError> {
            val errors = mutableListOf<ValidationError>()

            if (checkout.cancelUrl.trim().isEmpty() || !isValidUrl(checkout.cancelUrl)) {
                errors.add(ValidationError("cancelUrl", "Invalid or missing cancel URL."))
            }

            if (checkout.phone.trim().isEmpty() || !isNumeric(checkout.phone)) {
                errors.add(ValidationError("phone", "Invalid or missing phone number."))
            }

            if (checkout.email.trim().isEmpty() || !isValidEmail(checkout.email)) {
                errors.add(ValidationError("email", "Invalid or missing email address."))
            }

            if (checkout.errorUrl.trim().isEmpty() || !isValidUrl(checkout.errorUrl)) {
                errors.add(ValidationError("errorUrl", "Invalid or missing error URL."))
            }

            if (checkout.notifyUrl.trim().isEmpty() || !isValidUrl(checkout.notifyUrl)) {
                errors.add(ValidationError("notifyUrl", "Invalid or missing notify URL."))
            }

            if (checkout.successUrl.trim().isEmpty() || !isValidUrl(checkout.successUrl)) {
                errors.add(ValidationError("successUrl", "Invalid or missing success URL."))
            }

            if (checkout.paymentMethods.isEmpty()) {
                errors.add(ValidationError("paymentMethods", "At least one payment method is required."))
            }

            if (checkout.expireDate.trim().isEmpty()) {
                errors.add(ValidationError("expireDate", "Expiration date is required."))
            }

            if (checkout.items.isEmpty()) {
                errors.add(ValidationError("items", "At least one item is required."))
            }

            if (checkout.beneficiaries.isEmpty()) {
                errors.add(ValidationError("beneficiaries", "At least one beneficiary is required."))
            }

            if (checkout.lang.trim().isEmpty()) {
                errors.add(ValidationError("lang", "Language is required."))
            }

            return errors
        }

        private fun isValidUrl(url: String): Boolean {
            // Regular expression pattern for URL validation
            val pattern = """^https?:\/\/(?:www\.|(?!www))[^\s.]+\.[^\s]{2,}|www\.[^\s]+\.[^\s]{2,}$"""
            val regex = pattern.toRegex()
            return regex.matches(url)
        }

        private fun isValidEmail(email: String): Boolean {
            // Regular expression pattern for email validation
            val pattern = """^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$"""
            val regex = pattern.toRegex()
            return regex.matches(email)
        }

        private fun isNumeric(value: String): Boolean {
            // Regular expression pattern for numeric validation
            val pattern = """^\d+$"""
            val regex = pattern.toRegex()
            return regex.matches(value)
        }
    }
}