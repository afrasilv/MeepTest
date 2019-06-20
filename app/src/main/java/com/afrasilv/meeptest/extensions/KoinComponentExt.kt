package com.afrasilv.meeptest.extensions

import org.koin.core.KoinContext
import org.koin.standalone.StandAloneContext

/*
* Koin component marker interface
*/
interface KoinComponent

/* inject lazily given dependency
*
* @param name - bean name / optional
*/
inline fun <reified T : Any> KoinComponent.inject(name: String = "") = lazy {
    (StandAloneContext.koinContext as KoinContext).get<T>(name)
}