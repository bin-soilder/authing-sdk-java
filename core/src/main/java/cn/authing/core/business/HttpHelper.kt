package cn.authing.core.business


import cn.authing.core.http.Call
import cn.authing.core.result.ITokenResult
import cn.authing.core.result.LoginResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.Executor

internal class HttpHelper {

    private val contentType = MediaType.get("application/json; charset=utf-8")

    private val client: OkHttpClient = OkHttpClient()
    private val gson: Gson = Gson()
    private val executor: Executor? = Platform.platform.defaultCallbackExecutor()

    fun <T> createNormalCall(url: String, clazz: Class<T>): Call<T> {
        val request = Request.Builder()
                .url(url)
                .build()
        val adapter = gson.getAdapter(TypeToken.get(clazz))
        return delegateCall(NormalCall<T>(request, client, adapter))
    }

    fun <T> createAuthingCall(url: String, typeToken: TypeToken<AuthingResponse<T>>, param: Any, token: String? = null): Call<T> {
        val requestBuilder = Request.Builder()
                .url(url)
                .post(RequestBody.create(contentType, gson.toJson(param)))
        token?.let { requestBuilder.addHeader("Authorization", "Bearer $it") }
        val adapter = gson.getAdapter(typeToken)
        return delegateCall(AuthingCall(requestBuilder.build(), client, adapter))
    }

    fun <T : ITokenResult> createTokenCall(url: String, typeToken: TypeToken<AuthingResponse<T>>, param: Any, token: String? = null): Call<T> {
        val requestBuilder = Request.Builder()
                .url(url)
                .post(RequestBody.create(contentType, gson.toJson(param)))
        token?.let { requestBuilder.addHeader("Authorization", "Bearer $it") }
        val adapter = gson.getAdapter(typeToken)
        return delegateCall(TokenCall(requestBuilder.build(), client, adapter))
    }

    fun createLoginCall(url: String, param: Any): Call<LoginResult> {
        return createTokenCall(url, object : TypeToken<AuthingResponse<LoginResult>>() {}, param, null)
    }

    private fun <T> delegateCall(call: Call<T>): Call<T> {
        return if (executor == null) call else ExecutorCallbackCall(executor, call)
    }
}