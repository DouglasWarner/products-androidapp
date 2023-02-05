package com.douglas.domain

sealed class Errors: Throwable() {
    class NoProductsList : Errors()
    class NoConversionFound(override val message: String = "No conversion rate found from %s to %s"): Errors()
}