package com.blackbox.archiTemplate.ui.items

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.Keep
import android.support.annotation.NonNull
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import com.blackbox.archiTemplate.R
import com.blackbox.archiTemplate.utils.Constants
import com.blackbox.archiTemplate.utils.Utils
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by umair on 10/01/2018.
 */

@Keep
class PostItem() : AbstractItem<PostItem, PostItem.ViewHolder>(),Parcelable {

    var title = ""
    var details = ""
    var image = ""

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        details = parcel.readString()
        image = parcel.readString()
    }

    override fun getType(): Int {
        return R.id.fastadapter_post_item_id
    }


    override fun getLayoutRes(): Int {
        return R.layout.item_simple
    }

    override fun bindView(viewHolder: ViewHolder, @NonNull payloads: List<Any>?) {
        super.bindView(viewHolder, payloads!!)

        viewHolder.title.text = title
        viewHolder.details.text = details

        Glide.with(viewHolder.contentView.context)
                .load(image)
                .into(viewHolder.image);
    }

    override fun unbindView(@NonNull holder: ViewHolder?) {
        super.unbindView(holder!!)

    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        protected var view: View? = null

        var utils = Utils.create()

        internal var contentView: RelativeLayout
        internal var image: AppCompatImageView
        internal var title: AppCompatTextView
        internal var details: AppCompatTextView

        init {
            contentView = itemView.findViewById(R.id.content_view) as RelativeLayout
            image = itemView.findViewById(R.id.img) as AppCompatImageView
            title = itemView.findViewById(R.id.txt_title) as AppCompatTextView
            details = itemView.findViewById(R.id.txt_detail) as AppCompatTextView

            val context = contentView.context

            //Apply Fonts
            title.typeface = utils.setTypeface(Constants.FONT_ROBOTO_BOLD, context)
            details.typeface = utils.setTypeface(Constants.FONT_ROBOTO_REGULAR, context)

        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(details)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostItem> {
        override fun createFromParcel(parcel: Parcel): PostItem {
            return PostItem(parcel)
        }

        override fun newArray(size: Int): Array<PostItem?> {
            return arrayOfNulls(size)
        }
    }

}
