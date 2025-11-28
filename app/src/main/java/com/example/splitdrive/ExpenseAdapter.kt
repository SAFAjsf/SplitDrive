package com.example.splitdrive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(private val items: List<Expense>) : RecyclerView.Adapter<ExpenseAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.tv_expense_category)
        val amount: TextView = view.findViewById(R.id.tv_expense_amount)
        val paidBy: TextView = view.findViewById(R.id.tv_expense_paidby)
        val date: TextView = view.findViewById(R.id.tv_expense_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = items[position]
        holder.category.text = e.category
        holder.amount.text = String.format("$%.0f", e.amount)
        holder.paidBy.text = "Pagó: ${e.paidBy}"
        holder.date.text = e.date
    }

    override fun getItemCount(): Int = items.size
}
