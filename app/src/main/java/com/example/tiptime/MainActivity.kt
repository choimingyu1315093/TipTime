package com.example.tiptime

import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipTimeTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    TipTimeLayout()
                }
            }
        }
    }
}

private fun calculateTip(amount: Double): String{
    val tip = amount * 0.15
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun TipTimeLayout(modifier: Modifier = Modifier){
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0

    val tip = calculateTip(amount)
    Column (
        modifier = modifier
            .statusBarsPadding() //상단에 상태바 높이 만큼 패딩 추가됨(상태바에 가려지는 것을 방지)
            .padding(40.dp)
            .safeDrawingPadding(), //상태바, 내비게이션 바 등에 의해 가려지지 않게 패딩 추가됨
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = modifier.align(Alignment.Start).padding(bottom = 16.dp, top = 40.dp)
        )
        EditNumberField(amountInput, {amountInput = it})
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

@Composable
fun EditNumberField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = "Bill Amount")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.padding(bottom = 32.dp).fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}

@Preview(showBackground = true)
@Composable
fun EditNumberFieldPreview() {
    TipTimeTheme {
        EditNumberField("10", {})
    }
}