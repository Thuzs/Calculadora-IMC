package com.example.calculadoraimc

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable

fun IMCScreen(modifier: Modifier = Modifier) {
    var altura by remember { mutableStateOf(value = "") }
    var peso by remember { mutableStateOf(value = "") }
    var resultado by remember { mutableStateOf(value = "") }
    var classificao by remember {mutableStateOf(value = "")}
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier.fillMaxSize() ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // -- header --
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = colorResource(id = R.color.cor_app)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                    Image(
                        painter = painterResource(
                            R.drawable.bmi
                        ),
                        contentDescription = "IMC",
                        modifier = Modifier.size(80.dp)
                            .padding(vertical = 16.dp)
                    )
                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                // -- formulário --
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // -- card resultado --
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .offset(y = (-30).dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE7D7D7)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Spacer(modifier = Modifier.height(26.dp))
                            Text(
                                text = "Seus dados",
                                fontSize = 26.sp,
                            )
                            Spacer(modifier = Modifier.height(26.dp))
                            OutlinedTextField(
                                modifier = Modifier.width(300.dp),
                                value = altura,
                                onValueChange = { novoValor ->
                                    altura = novoValor
                                },

                                placeholder = {
                                    Text(text = "Altura")
                                }
                            )
                            Spacer(modifier = Modifier.height(22.dp))
                            OutlinedTextField(
                                modifier = Modifier.width(300.dp),
                                value = peso,
                                onValueChange = { novoValor ->
                                    peso = novoValor
                                },
                                placeholder = {
                                    Text(text = "Peso")
                                }
                            )
                            Spacer(modifier = Modifier.height(22.dp))
                            Button(
                                modifier = Modifier.width(300.dp),
                                onClick = {
                                   val valorIMC= calcularIMC(
                                       altura = altura.toDouble(),
                                       peso = peso.toDouble()
                                   )
                                    resultado = String.format("%.1f", valorIMC)
                                    if (valorIMC <18.5){
                                        classificao = "Abaixo do peso"
                                    } else if (valorIMC < 25 ){
                                        classificao = "Peso ideal"
                                    } else if (valorIMC < 30){
                                        classificao = "Levemente acima do peso"
                                    } else if (valorIMC < 35){
                                        classificao = "Obesidade grau 1"
                                    } else if (valorIMC < 40){
                                        classificao = "Obesidade grau 2"
                                    }else{
                                        classificao = "Obesidade grau 3"
                                    }
                                }
                            ) {
                                Text(
                                    text = "Calcular"
                                )
                            }

                        }
                    }
                    Text(
                        modifier = Modifier
                            .width(300.dp),
                        text = "$resultado $classificao",
                        fontSize = 30.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculadoraIMCTheme {
        IMCScreen()
    }
}