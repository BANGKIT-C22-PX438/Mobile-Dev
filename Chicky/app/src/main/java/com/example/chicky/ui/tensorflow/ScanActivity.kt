package com.example.chicky.ui.tensorflow

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.chicky.databinding.ActivityScanBinding
import com.example.chicky.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


class ScanActivity : AppCompatActivity() {
    private lateinit var binding:ActivityScanBinding

    private val GALLERY_REQUEST_CODE =123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoadImage.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg","image/png","image/jpg")
                intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                onResult.launch(intent)
            }
            else
            {
                requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }

        }
        binding.takePicture.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)
            {
                takePicturePreview.launch(null)

            }
            else
            {
                requestPermission.launch(android.Manifest.permission.CAMERA)
            }
        }


    }
    private val onResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        onResultReceived(GALLERY_REQUEST_CODE,it)
    }
    private fun onResultReceived(requestCode: Int,result:ActivityResult?)
    {
        when(requestCode)
        {
            GALLERY_REQUEST_CODE ->
            {

                if(result?.resultCode == Activity.RESULT_OK)
                {
                    result.data?.data?.let {
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                        binding.imageView12.setImageBitmap(bitmap)

                    }
                }
                else
                {
                    Log.e("TAG","error in selecting images")
                }
            }
        }
    }
    private fun outputGenerator(bitmap: Bitmap)
    {
        val resized = Bitmap.createScaledBitmap(bitmap,224,224,true)
        val model = Model.newInstance(this)
        val tensorImage = TensorImage(DataType.FLOAT32)

        tensorImage.load(resized)
        val byteBuffer = tensorImage.buffer
// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        //val outputs = model.process(inputFeature0)
        //val outputFeature0 = outputs.outputFeature0AsTensorBuffer

// Releases model resources if no longer used.
        model.close()
    }
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){granted ->
        if(granted)
        {
            takePicturePreview.launch(null)
        }
        else
        {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG
            ).show()
        }
    }
    private val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){bitmap ->
        if(bitmap!=null)
        {
            binding.imageView12.setImageBitmap(bitmap)
        }
    }

}