package `in`.ni3mumbaikar.save101.util

import android.content.Context
import android.util.Log
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.SecureCredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials

object AuthUtil {

    lateinit var account: Auth0

    private fun getAuthManager(context: Context):SecureCredentialsManager{
        val apiClient = AuthenticationAPIClient(this.account)
        val manager = SecureCredentialsManager(context,apiClient,SharedPreferencesStorage(context))
        return manager
    }

    fun initAuth(context: Context){
        Log.d("Auth0", "hasValidCredentials  : "+getAuthManager(context).hasValidCredentials().toString())
        if(!getAuthManager(context).hasValidCredentials()){
            loginWithBrowser(context)
        }
    }

    private fun loginWithBrowser(context : Context) {

        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(context, object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!
                }

                // Called when authentication completed successfully
                override fun onSuccess(result: Credentials) {
                    val accessToken = result.accessToken
                    Log.d("Auth0", "Saved Credentials done $result")
                    getAuthManager(context).saveCredentials(credentials = result)
                }
            })
    }

    fun logout(context:Context) {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(context, object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(result: Void?) {
                    getAuthManager(context).clearCredentials()
                }

                override fun onFailure(error: AuthenticationException) {
                    // Something went wrong!
                }
            })
    }



}