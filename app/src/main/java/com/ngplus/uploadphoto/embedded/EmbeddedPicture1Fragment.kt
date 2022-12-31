package com.ngplus.uploadphoto.embedded

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
import com.ngplus.uploadphoto.databinding.EmbeddedFragment1PictureBinding


class EmbeddedPicture1Fragment : Fragment() {

    private lateinit var _binding : EmbeddedFragment1PictureBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("tuto_picture","onCreateView ${javaClass.name}")
        // Inflate the layout for this fragment
        _binding = EmbeddedFragment1PictureBinding.inflate(inflater, container, false)

        _binding.BImage.setOnClickListener {
            getContent.launch("image/*")
        }
        return _binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("tuto_picture","onViewCreated ${javaClass.name}")

    }



    override fun onStart() {
        super.onStart()
        Log.i("tuto_picture","onStart ${javaClass.name}")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("tuto_picture","onAttach ${javaClass.name}")
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.i("tuto_photo","fired : ${javaClass.name}")
            val selectedImageUri: Uri? = uri

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