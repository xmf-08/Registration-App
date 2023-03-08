package xmf.developer.registration.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import xmf.developer.registration.DB.MyDbHelper
import xmf.developer.registration.R
import xmf.developer.registration.databinding.FragmentTizimgaKirishBinding

class TizimgaKirish : Fragment() {
    private val binding by lazy { FragmentTizimgaKirishBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        myDbHelper = MyDbHelper(binding.root.context)
        binding.tvRoyhatdanOtish.setOnClickListener {
            findNavController().navigate(R.id.royhatdanOtish)
        }
        binding.btnTizimgaKirish.setOnClickListener {
            for (i in myDbHelper.getAllShablons()) {
                if (binding.edtNumber.text.toString() == i.number.toString() && binding.edtPassword.text.toString() == i.password.toString()) {
                    findNavController().navigate(R.id.royhatdanOtganFoyFragment)
                }
            }
        }
        return binding.root
    }
}