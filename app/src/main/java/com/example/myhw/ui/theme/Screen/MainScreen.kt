import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhw.R
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(context: Context) {
    val (isDialogOpen, setDialogOpen) = remember { mutableStateOf(false) }
    val (text, setText) = remember { mutableStateOf("Текст") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier) {
            Image(
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp),
                painter = painterResource(id = R.drawable.png),
                contentDescription = ""
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    setDialogOpen(true)
                }

        )
        Spacer(modifier = Modifier.height(26.dp))

        Button(
            modifier = Modifier
                .width(128.dp)
                .padding(bottom = 32.dp),
            onClick = {
                showToast(context, "Click")
            }
        ) {
            Text(text = "Click")
        }

        if (isDialogOpen) {
            EditTextDialog(
                currentText = text,
                onTextChange = { newText -> setText(newText) },
                onDismiss = { setDialogOpen(false) },
                onSave = {
                    setDialogOpen(false)
                }
            )
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun EditTextDialog(
    currentText: String,
    onTextChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Введите текст")
        },
        text = {
            TextField(
                value = currentText,
                onValueChange = onTextChange,
                label = { Text("Введите ваш текст") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                modifier = Modifier.fillMaxSize()
            )
        },
        confirmButton = {
            Button(onClick = onSave) {
                Text(text = "Далее")
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreen_Preview() {
    MainScreen(context = LocalContext.current)
}
