package com.example.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingAdapter(
    private val items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.shopping_item,
            parent,
            false
        )
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        holder.itemView.txt_name.text = curShoppingItem.name
        holder.itemView.txt_amount.text = "${curShoppingItem.amount}"

        holder.itemView.iv_delete.setOnClickListener {
            viewModel.delete(curShoppingItem)
        }

        holder.itemView.iv_plus.setOnClickListener {
            curShoppingItem.amount++
            viewModel.upsert(curShoppingItem)
        }

        holder.itemView.iv_minus.setOnClickListener {
            if(curShoppingItem.amount > 0) {
                curShoppingItem.amount--
                viewModel.upsert(curShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    inner class  ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}
}