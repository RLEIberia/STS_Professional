package com.rle.STS.data

import retrofit2.HttpException
import kotlin.Exception


class DataOrException<T, Boolean, E> (
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null
)