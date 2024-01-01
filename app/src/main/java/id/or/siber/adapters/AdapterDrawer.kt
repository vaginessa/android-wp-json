package id.or.siber.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.or.siber.R
import id.or.siber.models.category.ModelCategoryItem

class AdapterDrawer(private var items: List<ModelCategoryItem>, private val itemClickListener: (ModelCategoryItem) -> Unit) : RecyclerView.Adapter<AdapterDrawer.DrawerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drawer, parent, false)
        return DrawerViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrawerViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class DrawerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.text_view_title)
        private val divider: View = view.findViewById(R.id.divider)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener(items[position])
                }
            }
        }

        fun bind(item: ModelCategoryItem) {
            if (item.name.toString() != "Tak Berkategori") {
                title.text = item.name
                divider.visibility = View.VISIBLE
            }
        }
    }

    fun updateItems(newItems: List<ModelCategoryItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}