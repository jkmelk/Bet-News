package com.example.betnews.base.model

data class ErrorResponse(val error: Error, val success: Int)

data class Error(val params: List<Params>, val type: String, val message: String)

data class Params(val message: String, val name: String)