package com.ngplus.uploadphoto

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ngplus.uploadphoto.databinding.Fragment1PictureBinding


class Picture1Fragment : Fragment() {

    private lateinit var _binding : Fragment1PictureBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment1PictureBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchSomeActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode
                == AppCompatActivity.RESULT_OK
            ) {
                val data = result.data
                // do your operation from here....
                if (data != null
                    && data.data != null
                ) {
                    val selectedImageUri: Uri? = data.data

                    try {
                        selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                            requireActivity().contentResolver,
                            selectedImageUri
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    _binding.IVImage.setImageBitmap(
                        selectedImageBitmap
                    )
                }
            }
        }
        _binding.BImage.setOnClickListener {
            imageChooser()
        }
    }

    private fun imageChooser() {
        val i = Intent()
        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT
        launchSomeActivity.launch(i)
    }

    override fun onStart() {
        super.onStart()
        Log.i("tuto_picture","onStart ${javaClass.name}")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("tuto_picture","onAttach ${javaClass.name}")
    }

    override fun onPause() {
        super.onPause()
        Log.i("tuto_picture","onPause ${javaClass.name}")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tuto_picture","onStop ${javaClass.name}")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tuto_picture","onResume ${javaClass.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tuto_picture","onDestroy ${javaClass.name}")
    }

}