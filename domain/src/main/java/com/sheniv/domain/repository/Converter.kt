package com.sheniv.domain.repository

interface Converter<INPUT, OUTPUT> {

    fun invoke(params: INPUT): OUTPUT
}