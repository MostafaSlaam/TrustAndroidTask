package trust.androidtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import trust.androidtask.R
import trust.androidtask.databinding.ItemRecyclerJobItemBinding
import trust.androidtask.model.Job


class RecyclerJobsAdapter(
    var items: ArrayList<Job>, var listener: OnRecyclerItemClickListener
) : RecyclerView.Adapter<RecyclerJobsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_recycler_job_item,
            parent,
            false
        ) as ItemRecyclerJobItemBinding
        return RecyclerJobsAdapter.ViewHolder(binding)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = items[holder.adapterPosition]
        holder.binding.model = item
        holder.binding.root.setOnClickListener {
            listener.onRecyclerItemClickListener(holder.adapterPosition)
        }
    }

    fun setList(list: ArrayList<Job>) {
        this.items = list
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemRecyclerJobItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


}