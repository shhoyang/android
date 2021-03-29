package com.hao.library.http

import com.google.gson.JsonParseException
import com.hao.library.HaoLibrary
import com.hao.library.utils.T
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

fun <D, T : HttpResponseModel<D>> Observable<T>.subscribeBy(
    onResponse: (D?) -> Unit,
    onFailure: (ResponseException) -> Unit = {},
    toastWhenSucceed: Boolean = false,
    toastWhenFailed: Boolean = false
): Disposable = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe({
        // 没有消费
        if (!HaoLibrary.CONFIG.httpConfig.handleResponse(it)) {
            if (it.isSucceed()) {
                if (toastWhenSucceed) {
                    toast(it.getMessage())
                }
                onResponse(it.getData())
            } else {
                if (toastWhenFailed) {
                    toast(it.getMessage())
                }
                onFailure(ResponseException(it.getCode(), it.getMessage(), it.getMessage()))
            }
        }
    }, {
        val error = processError(it)
        if (toastWhenFailed) {
            toast(error.errorMsg)
        }
        onFailure(error)
    })

fun <T> Observable<T>.subscribeBy2(
    onResponse: (T?) -> Unit,
    onFailure: (ResponseException) -> Unit = {},
    toastWhenFailed: Boolean = false
): Disposable = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe({
        onResponse(it)
    }, {
        val error = processError(it)
        if (toastWhenFailed) {
            toast(error.errorMsg)
        }
        onFailure(error)
    })

fun <D, T : HttpResponseModel<D>> Observable<T>.subscribeBy3(
    onResponse: (D?) -> Unit,
    onFailure: (ResponseException) -> Unit = {},
): Disposable = subscribe({
    // 没有消费
    if (!HaoLibrary.CONFIG.httpConfig.handleResponse(it)) {
        if (it.isSucceed()) {
            onResponse(it.getData())
        } else {
            onFailure(ResponseException(it.getCode(), it.getMessage(), it.getMessage()))
        }
    }
}, {
    onFailure(processError(it))
})

fun <T> Observable<T>.subscribeBy4(
    onResponse: (T?) -> Unit,
    onFailure: (ResponseException) -> Unit,
): Disposable =
    subscribe({
        onResponse(it)
    }, {
        onFailure(processError(it))
    })

fun toast(msg: String) {
    T.short(HaoLibrary.context, msg)
}

fun processError(throwable: Throwable): ResponseException {
    throwable.printStackTrace()
    if (throwable is HttpException) {
        var code = throwable.code().toString()
        return ResponseException(code, code, throwable.message())
    }
    if (throwable is CustomException) {
        val msg = throwable.message ?: HttpCode.UNKNOWN.errorMsg
        return ResponseException(HttpCode.UNKNOWN.errorCode, msg, msg)
    }
    return with(
        when (throwable) {
            is UnknownHostException -> HttpCode.UNKNOWN_HOST_EXCEPTION
            is ConnectException -> HttpCode.CONNECT_EXCEPTION
            is SocketException -> HttpCode.SOCKET_EXCEPTION
            is SocketTimeoutException -> HttpCode.SOCKET_TIMEOUT_EXCEPTION
            is SSLHandshakeException -> HttpCode.SSL_HANDSHAKE_EXCEPTION
            is JSONException -> HttpCode.JSON_EXCEPTION
            is JsonParseException -> HttpCode.JSON_EXCEPTION
            else -> HttpCode.UNKNOWN
        }
    ) {
        ResponseException(errorCode, errorMsg, throwable.message ?: errorMsg)
    }
}