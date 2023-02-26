package `in`.ni3mumbaikar.save101

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.auth0.android.Auth0
import `in`.ni3mumbaikar.save101.ui.theme.Save101Theme
import `in`.ni3mumbaikar.save101.util.AuthUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthUtil.account = Auth0(getString(R.string.com_autho_client_id),getString(R.string.com_auth0_domain))
        setContent {
            Save101Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
        AuthUtil.initAuth(this)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Test")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Greeting("Android")
    }
}