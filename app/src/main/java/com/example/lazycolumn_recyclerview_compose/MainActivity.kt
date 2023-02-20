package com.example.lazycolumn_recyclerview_compose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolumn_recyclerview_compose.ui.theme.LazyColumn_RecyclerView_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumn_RecyclerView_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val listaEmpleados = remember {
                        listOf(
                            Usuario("Sergio", "sergio@local.com", "Developer"),
                            Usuario("Ana", "ana@local.com", "Cajera"),
                            Usuario("juan", "juan@local.com", "Almacenista")
                        )
                    }
                    MostrarUsuarios(listaEmpleados)
                }
            }
        }
    }
}

@Composable
fun MostrarUsuarios(listaEmpleados: List<Usuario>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(listaEmpleados) { empleado ->
            CardEmpleados(empleado = empleado)
        }
    }
}

@Composable
fun CardEmpleados(empleado: Usuario) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = Color.DarkGray,
        contentColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable { mostrarMensaje(context = context, empleado.nombre) },
            Arrangement.Center,
        ) {
            Text(
                text = empleado.nombre,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = empleado.email,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = empleado.puesto,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

fun mostrarMensaje(context: Context, nombre: String) {
    Toast.makeText(context, "Presionaste la Card del empleado: $nombre", Toast.LENGTH_LONG).show()
}
