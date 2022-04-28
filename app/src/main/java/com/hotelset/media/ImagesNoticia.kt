package com.hotelset.media

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.FileProvider
import com.google.firebase.BuildConfig
import java.io.File

class ImagesNoticia (
    private val contexto: Context,
    btAddImagesN: ImageButton,
    private val imagen: ImageView,
    private var tomarFotoActivity: ActivityResultLauncher<Intent>
) {
    init {
        btAddImagesN.setOnClickListener { tomarFotoN() }
    }


    lateinit var imagenFile: File
    private lateinit var currentPhotoPath: String

    @SuppressLint("QueryPermissionsNeeded")
    private fun tomarFotoN() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(contexto.packageManager) != null) {
            imagenFile = createImageFile()
            val photoURI = FileProvider.getUriForFile(
                contexto,
                BuildConfig.APPLICATION_ID + ".provider",
                imagenFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            tomarFotoActivity.launch(intent)
        }
    }

    private fun createImageFile(): File {
        val archivo=OtrosUtiles.getTempFile("imagen_")
        val storageDir: File? =
            contexto.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image= File.createTempFile(
            archivo,
            ".jpg",
            storageDir    )
        currentPhotoPath = image.absolutePath
        return image
    }

    fun actualizaFotoN() {
        imagen.setImageBitmap(
            BitmapFactory.decodeFile(imagenFile.absolutePath))
    }
}








