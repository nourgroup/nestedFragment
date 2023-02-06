package com.ngplus.uploadphoto

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ngplus.uploadphoto.databinding.ActivityMainBinding
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : FragmentActivity() {

    lateinit var _binding : ActivityMainBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        /*******************/
        // LogNotTimber
        Log.d("TAG_uploadphoto", "msg")
        Log.d("TAG", "msg", Exception())
        Log.d("TAG", "msg")
        Log.d("TAG", "msg", Exception())

        // StringFormatInTimber
        //Timber.w(String.format("%s", getString()))
        //Timber.w(format("%s", getString()))

        // ThrowableNotAtBeginning
        Timber.d("%s", Exception())

        // BinaryOperationInTimber
        val foo = "foo"
        val bar = "bar"
        Timber.d("foo" + "bar")
        Timber.d("foo$bar")
        Timber.d("${foo}bar")
        Timber.d("$foo$bar")

        // TimberArgCount
        Timber.d("%s %s", "arg0")
        Timber.d("%s", "arg0", "arg1")
        //Timber.tag("tag").d("%s %s", "arg0")
        //Timber.tag("tag").d("%s", "arg0", "arg1")

        // TimberArgTypes
        Timber.d("%d", "arg0")
        //Timber.tag("tag").d("%d", "arg0")

        // TimberTagLength
        Timber.tag("abcdefghijklmnopqrstuvwx")
        Timber.tag("abcdefghijklmnopqrstuvw" + "x")

        // TimberExceptionLogging
        Timber.d(Exception(), Exception().message)
        Timber.d(Exception(), "")
        Timber.d(Exception(), null)
        Timber.d(Exception().message)

        /*******************/

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                imageChooser()
                Toast.makeText(this, "permission is granted !", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "permission not granted !", Toast.LENGTH_SHORT).show()
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their decision.
            }
        }

        launchSomeActivity = registerForActivityResult(
            StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode
                == RESULT_OK
            ) {
                val data = result.data
                // do your operation from here....
                if (data != null && data.data != null) {
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
            //requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            //requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            //val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //startActivityForResult(intent, 0)
            imageChooser()
        }

        /**
         *
         */
        var a  = findNavController(R.id.main_fragment)
        _binding.bnv.setupWithNavController(a)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val bitmap = data?.extras!!["data"] as Bitmap?
        _binding.IVPreviewImage.setImageBitmap(bitmap)
        Log.i("yes","${bitmap?.byteCount}")
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
        //val i = Intent()
        //i.type = "image/*"
        /*i.action = Intent.ACTION_GET_CONTENT
        launchSomeActivity.launch(i)*/
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
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
                        this,
                        "com.ngplus.uploadphoto",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                    //UC1.5
                    //launchSomeActivity.launch(takePictureIntent)
                    startActivityForResult(takePictureIntent, 0)
                }
            }
        }
        //launchSomeActivity.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }
    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
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
}