package com.example.tiptime

import android.util.Log
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
   @Test
   fun calculator_20PercentRoundUp(){
       val amount = 10.0
       val tipPercent = 20.0
       val roundUp = false
       val tip = tipPercent/100*amount
       val expectedTip = NumberFormat.getCurrencyInstance().format(tip)
       val actualTip = calculateTip(amount, tipPercent, roundUp)
       assertEquals(expectedTip, actualTip)
   }
}