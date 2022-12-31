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
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.ngplus.uploadphoto.Picture2Fragment
import com.ngplus.uploadphoto.databinding.EmbeddedFragment2PictureBinding
import android.graphics.Movie

import android.content.Intent.getIntent
import android.os.Parcelable


class EmbeddedPicture2Fragment : Fragment(){

    lateinit var observer : MyLifecycleObserver

    private lateinit var _binding : EmbeddedFragment2PictureBinding
    var selectedImageBitmap: Bitmap? = null
    lateinit var launchSomeActivity : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("tuto_picture","onCreateView ${javaClass.name}")
        // Inflate the layout for this fragment
        _binding = EmbeddedFragment2PictureBinding.inflate(inflater, container, false)
        /*observer = MyLifecycleObserver(requireActivity().activityResultRegistry){
            val selectedImageUri: Uri? = it
            Log.i("tuto_photo","fired : ${javaClass.name}")
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
        lifecycle.addObserver(observer)*/
        val uri =  (parentFragment as Picture2Fragment).uri
        if (uri != null) {

            uri.let {
                try {
                    selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                        requireActivity().contentResolver,
                        it
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                _binding.IVImage.setImageBitmap(
                    selectedImageBitmap
                )
            }
        }else{
            Log.i("tuto_photo","not here!!!!!!!!")
        }


        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tuto_photo","onViewCreated")
        Log.i("tuto_picture","onViewCreated ${javaClass.name}")

        _binding.BImage.setOnClickListener {
            //observer.selectImage()
            (parentFragment as Picture2Fragment).ListenerClick()
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

    class MyLifecycleObserver(private val registry : ActivityResultRegistry,val callback : (Uri?) -> Unit)
        : DefaultLifecycleObserver {
        lateinit var getContent : ActivityResultLauncher<String>

        override fun onCreate(owner: LifecycleOwner) {
            getContent = registry.register("key", owner, ActivityResultContracts.GetContent()) { uri ->
                Log.i("tuto_photo","done !!")
                callback(uri)
            }
        }

        fun selectImage() {
            Log.i("tuto_photo","done !!!")
            getContent.launch("image/*")
        }
    }

    fun listenerPicture(uri : Uri?) {
        Log.i("tuto_photo","clicked me hha!!")
        /*try {
            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                requireActivity().contentResolver,
                uri
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        _binding.IVImage.setImageBitmap(
            selectedImageBitmap
        )*/
    }


}