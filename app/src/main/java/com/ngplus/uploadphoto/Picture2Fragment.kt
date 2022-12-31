package com.ngplus.uploadphoto

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.ngplus.uploadphoto.databinding.Fragment2PictureBinding
import com.ngplus.uploadphoto.embedded.EmbeddedPicture1Fragment
import com.ngplus.uploadphoto.embedded.EmbeddedPicture2Fragment
import com.ngplus.uploadphoto.embedded.EmbeddedPicture3Fragment
import com.ngplus.uploadphoto.embedded.MyListener


class Picture2Fragment : Fragment(),MyListener {


    lateinit var mEmbeddedFragment1Picture : EmbeddedPicture1Fragment
    lateinit var mEmbeddedFragment2Picture : EmbeddedPicture2Fragment
    lateinit var mEmbeddedFragment3Picture : EmbeddedPicture3Fragment
    private lateinit var _binding : Fragment2PictureBinding
     var uri : Uri? = null
    var selectedImageBitmap: Bitmap? = null
    lateinit var getContent : ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment2PictureBinding.inflate(inflater, container, false)
        Log.i("tuto_picture","onCreateView ${javaClass.name}")
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("tuto_picture","onViewCreated ${javaClass.name}")
        mEmbeddedFragment1Picture = EmbeddedPicture1Fragment()
        mEmbeddedFragment2Picture = EmbeddedPicture2Fragment()
        mEmbeddedFragment3Picture = EmbeddedPicture3Fragment()

        setCurrentFragment(mEmbeddedFragment1Picture,null)
        _binding.b1.setOnClickListener {
            setCurrentFragment(mEmbeddedFragment1Picture,null)
        }
        _binding.b2.setOnClickListener {
            setCurrentFragment(mEmbeddedFragment2Picture,null)
        }
        _binding.b3.setOnClickListener {
            setCurrentFragment(mEmbeddedFragment3Picture,null)
        }
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            Log.i("tuto_photo","fired : ${javaClass.name}")
            /*val selectedImageUri: Uri? = uri

            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                    requireActivity().contentResolver,
                    selectedImageUri
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }*/
            /*_binding.IVImage2.setImageBitmap(
                selectedImageBitmap
            )*/
            this.uri = uri
            setCurrentFragment(mEmbeddedFragment2Picture,uri)

        }

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

    override fun onResume() {
        super.onResume()
        Log.i("tuto_picture","onResume ${javaClass.name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("tuto_picture","onDestroy ${javaClass.name}")
    }

    private fun setCurrentFragment(fragment : Fragment,uri :Uri?) {
        val bundle = Bundle()
        uri.let {
            bundle.putParcelable("pic",uri)
        }
        childFragmentManager.beginTransaction().apply {
            replace(R.id.fl,fragment)
            //setReorderingAllowed(true)

            arguments =bundle
            addToBackStack(javaClass.name) // name can be null
            commit()
        }
    }

    override fun ListenerClick() {
        Log.i("tuto_photo","clicked")
        getContent.launch("image/*")

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        /**
         *
         */
        //mListenerChild.init(this)
        Log.i("tuto_picture","onAttach ${javaClass.name}")

    }
}