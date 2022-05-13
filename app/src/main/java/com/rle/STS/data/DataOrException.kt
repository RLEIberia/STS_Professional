package com.rle.STS.data

import java.lang.Exception


class DataOrException<T, Boolean, E: Exception> (

    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null)