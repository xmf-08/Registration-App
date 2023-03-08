package xmf.developer.registration.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.contains
import androidx.navigation.fragment.findNavController
import xmf.developer.registration.DB.MyDbHelper
import xmf.developer.registration.R
import xmf.developer.registration.databinding.FragmentRoyhatdanOtishBinding
import xmf.developer.registration.databinding.ItemRvBinding
import xmf.developer.registration.models.MyShablon

class RoyhatdanOtish : Fragment() {
    private  var picturepath: String? = null
    private val binding by lazy { FragmentRoyhatdanOtishBinding.inflate(layoutInflater) }
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var imagebitmap :String
    lateinit var myDbHelper: MyDbHelper
    private val itemRvBinding by lazy { ItemRvBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        myDbHelper = MyDbHelper(binding.root.context)

        binding.imgggg.setOnClickListener {
            choosePhotoFromGallary()
        }


        binding.btnRoyhatdanOtish.setOnClickListener {
            if (binding.edtNameLastname.text.toString().isEmpty() ||
                binding.edtNumber.text.toString().isEmpty() ||
                binding.edtAddress.text.toString().isEmpty() ||
                binding.edtPassword.text.toString().isEmpty()||picturepath==null
            ) {
                Toast.makeText(binding.root.context, "Bo'sh joylarni toldiring", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val myShablon = MyShablon(
                    binding.edtNameLastname.text.toString().trim(),
                    binding.edtNumber.text.toString().trim(),
                    binding.edtAddress.text.toString().trim(),
                    binding.edtPassword.text.toString().trim(),
                    picturepath
                )
                myDbHelper.addShablon(myShablon)
                findNavController().navigate(R.id.royhatdanOtganFoyFragment)
                Toast.makeText(binding.root.context, "Saqlandi", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

//    private fun showPictureDialog() {
//        val pictureDialog = AlertDialog.Builder(requireContext())
//        pictureDialog.setTitle("Select Action")
//        val pictureDialogItems = arrayOf("Select photo from gallery")
//        pictureDialog.setItems(
//            pictureDialogItems
//        ) { _, which ->
//            when (which) {
//                0 -> choosePhotoFromGallary()
//            }
//        }
//        pictureDialog.show()
//    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, 1)
    }


//    @SuppressLint("QueryPermissionsNeeded")
//    private fun takePhotoFromCamera() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
//                startActivityForResult(takePictureIntent, 100)
//            }
//        }
//    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = requireContext().contentResolver.query(
                selectedImage!!,
                filePathColumn,
                null,
                null,
                null
            )
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            picturepath = cursor.getString(columnIndex)
            cursor.close()
            binding.imgggg.setImageBitmap(BitmapFactory.decodeFile(picturepath))
            Toast.makeText(requireContext(), picturepath, Toast.LENGTH_SHORT).show()
        }
    }
}