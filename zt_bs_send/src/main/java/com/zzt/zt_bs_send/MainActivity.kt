package com.zzt.zt_bs_send

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.zzt.zt_bs_send.ui.theme.ZT_BroadcastReceiverTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZT_BroadcastReceiverTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val ACTION_RECEIVE = "com.RECEIVE"
    val ACTION_DATA = "com.DATA"
    val TAG = "ZZZZZZZ"

    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = {
            scope.launch {
                withContext(Dispatchers.IO) {
                    while (true) {
                        val random = Random()
                        val sendMsg = "发送内容 轮询:" + random.nextInt(1000)

                        val intent: Intent = Intent(ACTION_RECEIVE)
                        intent.putExtra(ACTION_DATA, sendMsg)
                        intent.setPackage("com.zzt.broadcastreceiver")
                        context.sendBroadcast(intent)
                        Log.w(TAG, "开始发送 > 轮询 > " + sendMsg)


                        delay(2000L)
                    }
                }
            }
        }) {
            Text("连续发送广播")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZT_BroadcastReceiverTheme {
        Greeting("Android")
    }
}