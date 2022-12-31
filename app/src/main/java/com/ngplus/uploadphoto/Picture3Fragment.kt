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
import com.ngplus.uploadphoto.databinding.Fragment3PictureBinding


class Picture3Fragment : Fragment() {

    private lateinit var _binding : Fragment3PictureBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment3PictureBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
        Log.i("tuto_picture","onStart f")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("tuto_picture","onAttach f")
    }

    override fun onPause() {
        super.onPause()
        Log.i("tuto_picture","onPause f")
    }

    override fun onStop() {
        super.onStop()
        Log.i("tuto_picture","onStop f")
    }

    override fun onResume() {
        super.onResume()
        Log.i("tuto_picture","onResume f")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tuto_picture","onDestroy f")
    }

}