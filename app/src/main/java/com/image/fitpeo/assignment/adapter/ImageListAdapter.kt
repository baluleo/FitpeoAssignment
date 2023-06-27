package com.image.fitpeo.assignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.image.fitpeo.assignment.R
import com.image.fitpeo.assignment.databinding.ThumbnailItemBinding
import com.image.fitpeo.assignment.model.ImagesModel
import com.image.fitpeo.assignment.model.OnClickListener
import com.squareup.picasso.Picasso

class ImageListAdapter : RecyclerView.Adapter<ImageViewHolder>()  {
    private var imagesModels = mutableListOf<ImagesModel>()
    private var onClickListener: OnClickListener? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setImageList(images: List<ImagesModel>) {
        this.imagesModels = images.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(ThumbnailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imagesModels[position]
        holder.apply {
            bind(imagesModels[position])
        }
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item )
            }
        }
    }

    override fun getItemCount(): Int {
        return imagesModels.size
    }
    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["loadImage"], requireAll = false)
        fun loadImage(image: ImageView, url: String) {

            Picasso.get().load(url)
                .fit()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(image)
        }
    }
}
class ImageViewHolder(private val binding: ThumbnailItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: ImagesModel) {
        binding.image= image
    }

}
