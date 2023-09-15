package com.poklad.jobinterviewtestproject.di.annotations

import javax.inject.Scope

/**
 * Custom scope for dependencies that should live as long as the Application.
 * Use this scope for dependencies that are shared across the entire application,
 * and should be alive for the entire lifetime of the application.
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope
