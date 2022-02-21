package com.joseferreyra.common.utils

sealed class Failure {
    object FeatureFailure : Failure()
    object NetworkFailure : Failure()
    object ServerError : Failure()
}
