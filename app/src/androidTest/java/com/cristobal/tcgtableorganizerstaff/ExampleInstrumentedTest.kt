package com.cristobal.tcgtableorganizerstaff

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test para validar el contexto real de la app.
 * Se ejecuta en un dispositivo Android.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun appContext_isCorrect() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.cristobal.tcgtableorganizerstaff", context.packageName)
    }
}
