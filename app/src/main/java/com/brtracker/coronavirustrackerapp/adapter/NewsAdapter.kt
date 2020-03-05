package com.brtracker.coronavirustrackerapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.model.ArticleModel
import com.brtracker.coronavirustrackerapp.ui.WebViewActivity
import com.brtracker.coronavirustrackerapp.util.NEWS_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.news_item_layout.view.*


class NewsAdapter(val context: Context) :

    PagedListAdapter<ArticleModel, NewsAdapter.ViewHolder>(USER_COMPARATOR) {

    private val TAG = NewsAdapter::class.java.simpleName


/*    private val sharedViewModel: SharedViewModel =
        ViewModelProvider(context as FragmentActivity).get(
            SharedViewModel::class.java
        )*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item_layout, parent, false)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(item) }
    }

    class ViewHolder(
        itemView: View,
        val context: Context
    ) :
        RecyclerView.ViewHolder(itemView) {

        val imageView = itemView.news_image
        val title = itemView.title
        val source = itemView.source
        val updated = itemView.updated

        fun bind(data: ArticleModel) {
            // var bundle = bundleOf("imageEntity" to data)
            itemView.setOnClickListener {


                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra(NEWS_URL, data.url);
                (context as FragmentActivity).startActivity(intent)
                context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

            title.text=data.description
            source.text=data.source
            updated.text = data.publishedAt

            Glide.with(imageView.context)
                .load(data.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop()
                .into(imageView)


        }

    }


    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<ArticleModel>() {
            override fun areItemsTheSame(
                oldItem: ArticleModel,
                newItem: ArticleModel
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: ArticleModel,
                newItem: ArticleModel
            ): Boolean =
                newItem == oldItem
        }
    }
}