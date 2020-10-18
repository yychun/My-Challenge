package com.yychun1217.mychallenge

import org.junit.After
import org.junit.Before
import org.mockito.MockitoAnnotations

open class BaseTestCase {
    private lateinit var autoCloseable: AutoCloseable

    @Before
    fun openMocks() {
        autoCloseable = MockitoAnnotations.openMocks(this)
    }

    @After
    fun releaseMocks() {
        autoCloseable.close()
    }
}