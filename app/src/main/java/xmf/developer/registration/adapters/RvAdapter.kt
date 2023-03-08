package xmf.developer.registration.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xmf.developer.registration.databinding.ItemRvBinding
import xmf.developer.registration.models.MyShablon

class RvAdapter( val usersList: ArrayList<MyShablon>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myShablon: MyShablon, position: Int) {
            itemRvBinding.apply {
                tvIsmFamily.text = myShablon.fullName
                tvNumber.text = myShablon.number
                imgItem.setImageBitmap(BitmapFactory.decodeFile(myShablon.image))
                itemRvBinding.root.setOnClickListener { rvClick.itemClicked(myShablon) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) =
        holder.onBind(usersList[position], position)

    override fun getItemCount(): Int = usersList.size
}
interface RvClick {

    fun itemClicked(myShablon: MyShablon)
    fun itemMenuClicked(myShablon: MyShablon, view: View, position: Int)

}