package me.tsalikis.blog.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.tsalikis.blog.PostDescription

class PostsAdapter(private val posts: List<PostDescription>): RecyclerView.Adapter<PostDescriptionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDescriptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_post_entry, parent, false)
        return PostDescriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostDescriptionViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

}

class PostDescriptionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.title)
    private val description = itemView.findViewById<TextView>(R.id.description)
    private val publishDate = itemView.findViewById<TextView>(R.id.publishDate)

    fun bind(postDescription: PostDescription) {
        title.text = postDescription.title
        description.text = postDescription.summary
        publishDate.text = postDescription.publishDate
    }

}