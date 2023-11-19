package com.example.projecttodolist

import androidx.navigation.NavController
import com.example.projecttodolist.Navigation.AppScreens
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun typeassignation() {
        assertEquals("Personal", typeAssignation(true))

    }

    @Test
    fun duration(){
        assertEquals("2023-11-18", durationCalc(LocalDate.now().toString() , LocalDate.now().toString()))
    }

private fun typeAssignation(type : Boolean) : String{
    if (type) {
        return "Personal"
    } else {
        return "Grupal"
    }


}

   private fun durationCalc(dateI : String, dateF : String): Long {
        val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        val fechaI = LocalDate.parse(dateI, formatoFecha)
        val fechaF = LocalDate.parse(dateF, formatoFecha)

        val duration = ChronoUnit.DAYS.between(fechaI, fechaF) + 1

        return duration
    }


}