package com.ngplus.uploadphoto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ngplus.uploadphoto.databinding.ActivityMainBinding
import android.content.Intent
import android.provider.MediaStore

import android.graphics.Bitmap

import android.app.Activity
import android.net.Uri
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback

import androidx.activity.result.contract.ActivityResultContracts

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController


class MainActivity : FragmentActivity() {

    lateinit var _binding : ActivityMainBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        launchSomeActivity = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode
                == RESULT_OK
            ) {
                val data = result.data
                // do your operation from here....
                if (data != null
                    && data.data != null
                ) {
                    val selectedImageUri: Uri? = data.data

                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            this.contentResolver,
                            selectedImageUri
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    _binding.IVPreviewImage.setImageBitmap(
                        selectedImageBitmap
                    )
                }
            }
        }
        _binding.BSelectImage.setOnClickListener {
            imageChooser()
        }

        /**
         *
         */
        var a  = findNavController(R.id.main_fragment)
        _binding.bnv.setupWithNavController(a)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.i("tuto_picture","onAttachedToWindow ${javaClass.name}")
    }

    override fun onStart() {
        super.onStart()
        Log.i("tuto_picture","onStart ${javaClass.name}")
    }

    override fun onPause() {
        super.onPause()
        Log.i("tuto_picture","onPause ${javaClass.name}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tuto_picture","onStop ${javaClass.name}")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("tuto_picture","onRestart ${javaClass.name}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tuto_picture","onResume ${javaClass.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tuto_picture","onDestroy ${javaClass.name}")
    }

    private fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        launchSomeActivity.launch(i)
    }
}