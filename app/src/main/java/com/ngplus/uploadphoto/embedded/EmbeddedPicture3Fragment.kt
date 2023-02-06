package com.ngplus.uploadphoto.embedded

import android.Manifest
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.ngplus.uploadphoto.databinding.EmbeddedFragment3PictureBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class EmbeddedPicture3Fragment : Fragment() {

    private lateinit var _binding : EmbeddedFragment3PictureBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("tuto_photo","onCreateView ${javaClass.name}")
        // Inflate the layout for this fragment
        _binding = EmbeddedFragment3PictureBinding.inflate(inflater, container, false)
        launchSomeActivity = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            Log.i("tuto_photo","here")
            //result.resultCode== AppCompatActivity.RESULT_OK
            if (true) {
                Log.i("tuto_photo","here")
                val data = result.data
                // do your operation from here....
                if (data != null && data.data != null) {
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
                    Log.i("tuto_photo","here")
                }
            }
        }
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tuto_picture","onViewCreated ${javaClass.name}")
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                imageChooser()
                Toast.makeText(activity, "permission is granted !", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity, "permission not granted !", Toast.LENGTH_SHORT).show()
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their decision.
            }
        }

        _binding.BImage.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun imageChooser() {
        //val i = Intent()
        //i.type = "image/*"
        /*i.action = Intent.ACTION_GET_CONTENT
        launchSomeActivity.launch(i)*/
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.ngplus.uploadphoto",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    //UC1.5
                    launchSomeActivity.launch(takePictureIntent)
                    //startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            Log.i("SLDF_Olinge"," absolutePath: $absolutePath")
        }
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