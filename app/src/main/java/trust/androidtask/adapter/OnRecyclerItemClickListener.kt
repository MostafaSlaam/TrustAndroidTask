package trust.androidtask.adapter

interface OnRecyclerItemClickListener {
    fun onRecyclerItemClickListener(position: Int)
    fun toggleFavourite(position: Int)
}