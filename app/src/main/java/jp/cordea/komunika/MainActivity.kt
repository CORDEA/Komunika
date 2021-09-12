package jp.cordea.komunika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import jp.cordea.komunika.ui.KomunikaApp
import jp.cordea.komunika.ui.theme.KomunikaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KomunikaTheme {
                KomunikaApp()
            }
        }
    }
}
