package com.example.android_films_app.presentation.viewModel

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_films_app.domain.usecase.storage.proto.SubscribeGetUserUseCase
import com.example.android_films_app.presentation.mapper.user.UserToUserItemMapper
import com.example.android_films_app.presentation.state.ProfileScreenState
import com.example.android_films_app.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

/**
 * @author Lapoushko
 */
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val subscribeGetUserUseCase: SubscribeGetUserUseCase,
    private val userToUserItemMapper: UserToUserItemMapper
): ViewModel() {
    private val _userState = MutableProfileScreenState()
    val userState = _userState as ProfileScreenState

    init {
        load()
    }

    fun download(
        url: Uri,
        context: Context
    ) {
        val request: DownloadManager.Request = DownloadManager.Request(url)
        with(request) {
            setTitle("File")
            setMimeType("mimi")
            setDescription("Download")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                NAME_FILE
            )
        }
        val manager: DownloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)
    }

    fun open(context: Context){
        try {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ),
                NAME_FILE
            )
            val uri = FileProvider.getUriForFile(
                context,
                context.applicationContext?.packageName + ".provider",
                file
            )
            val intent =
                Intent(Intent.ACTION_VIEW)
            with(intent) {
                setDataAndType(
                    uri,
                    "application/pdf"
                )
                flags =
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(intent)
            Log.e(Constants.LOG_KEY, "OK")
        } catch (e: Exception) {
            Log.e(Constants.LOG_KEY, e.toString())
            e.printStackTrace()
        }
    }

    private fun load() {
        viewModelScope.launch {
            subscribeGetUserUseCase.get().collect {
                userToUserItemMapper.invoke(it).apply {
                    _userState.name = name
                    _userState.description = description
                    _userState.photoUrl = photoUrl
                    _userState.resumeUrl = resumeUrl
                }
            }
        }
    }
    private class MutableProfileScreenState: ProfileScreenState {
        override var name: String by mutableStateOf("")
        override var description: String
            by mutableStateOf("")
        override var photoUrl: Uri
            by mutableStateOf(Uri.EMPTY)
        override var resumeUrl: Uri
            by mutableStateOf(Uri.parse("https://elibrary.ru/download/elibrary_44394445_69180276.pdf"))
    }

    companion object{
        const val NAME_FILE = "file.pdf"
    }
}