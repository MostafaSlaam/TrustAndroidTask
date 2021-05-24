package trust.androidtask.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import trust.androidtask.R
import trust.androidtask.databinding.ItemRecyclerJobBinding
import trust.androidtask.model.Job


class RecyclerJobsAdapter(
    var items: ArrayList<Job>, var listener: OnRecyclerItemClickListener
) : RecyclerView.Adapter<RecyclerJobsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_recycler_job,
            parent,
            false
        ) as ItemRecyclerJobBinding
        return RecyclerJobsAdapter.ViewHolder(binding)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[holder.adapterPosition]
        holder.binding.model = item
        holder.binding.root.setOnClickListener {
            listener.onRecyclerItemClickListener(holder.adapterPosition)
        }
        holder.binding.btnToggoleFav.setOnClickListener {
            if (item.isFav)
                holder.binding.btnToggoleFav.setImageResource(R.drawable.unlike_icon)
            else
                holder.binding.btnToggoleFav.setImageResource(R.drawable.like_icon)
            item.isFav = !item.isFav!!
            listener.toggleFavourite(holder.adapterPosition)
        }
    }

    fun setList(list: ArrayList<Job>) {
        this.items = list
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemRecyclerJobBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}