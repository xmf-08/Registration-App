package xmf.developer.registration.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import xmf.developer.registration.DB.MyDbHelper
import xmf.developer.registration.R
import xmf.developer.registration.adapters.RvAdapter
import xmf.developer.registration.adapters.RvClick
import xmf.developer.registration.databinding.FragmentRoyhatdanOtganFoyBinding
import xmf.developer.registration.databinding.FragmentRoyhatdanOtishBinding
import xmf.developer.registration.databinding.Item45Binding
import xmf.developer.registration.models.MyShablon

class RoyhatdanOtganFoyFragment : Fragment(), RvClick {
    private val binding by lazy { FragmentRoyhatdanOtganFoyBinding.inflate(layoutInflater) }
    lateinit var myDbHelper: MyDbHelper
    lateinit var rvAdapter: RvAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        myDbHelper = MyDbHelper(binding.root.context)
        rvAdapter = RvAdapter(myDbHelper.getAllShablons(),this )
        binding.rv.adapter = rvAdapter
        return binding.root
    }
    override fun itemClicked(myShablon: MyShablon) {
        val dialoger = Item45Binding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        dialoger.tvIsmFamily.text = myShablon.fullName
        dialoger.tvManzil.text = myShablon.address
        dialoger.imgItem.setImageBitmap(BitmapFactory.decodeFile(myShablon.image))
        dialog.setContentView(dialoger.root)
        dialog.dismissWithAnimation = true

        dialog.setCancelable(true)

        dialoger.phone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + myShablon.number)
            startActivity(dialIntent)
        }
        dialoger.sms.setOnClickListener {
            val dialSms = Intent(Intent.ACTION_SENDTO)
            dialSms.data = Uri.parse("smsto: " + myShablon.number)
            startActivity(dialSms)
        }
        dialog.show()

    }

    override fun itemMenuClicked(myShablon: MyShablon, view: View, position: Int) {
        Toast.makeText(binding.root.context, "Endi qilaman", Toast.LENGTH_SHORT).show()
    }
}