package com.example.domain.repository

interface Converter<INPUT, OUTPUT> {

    fun invoke(params: INPUT): OUTPUT
}