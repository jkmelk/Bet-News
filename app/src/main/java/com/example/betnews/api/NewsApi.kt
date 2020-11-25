package com.example.betnews.api

import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NewsApi : KoinComponent {

    val authenticationApi: NewsSearchCall by inject { parametersOf(BASE_URL) }
}