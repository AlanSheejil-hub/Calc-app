package com.nst.calc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nst.calc.databinding.CalculatorButtonBinding

    //Calculator adapter class for the recycler view
class CalculatorAdapter(private val buttons: Array<String>, val listener: Listener) :
    RecyclerView.Adapter<CalculatorAdapter.ViewHolder>() {

    //interface to interact with another interface

    interface Listener {
        fun onClick(position: Int)
    }

    /*
    * create a view holder with viewBinding
    * Binding with the xml button class
    * Initialize the listener for the viewHolder
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = CalculatorButtonBinding.inflate(LayoutInflater.from(parent.context))
        val viewHolder = ViewHolder(viewBinding)
        viewHolder.initializeListener()
        return viewHolder
    }
    //Binding the viewHolder with the data of the buttons
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateViewWithData(
            buttonText = buttons[position]
        )
    }
    // getting the total count of the buttons

    override fun getItemCount(): Int {
        return buttons.size
    }
   /*
   * ViewHolder class has two functions initializeListener and updateViewWithData
   * initializeListener handles the button click
   * updateViewWithData map the buttons of string with the button class in the xml
   */
    inner class ViewHolder(private val itemViewBinding: CalculatorButtonBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun initializeListener() {
            itemViewBinding.button.setOnClickListener {
                listener.onClick(bindingAdapterPosition)
            }
        }

        fun updateViewWithData(buttonText: String) {
            itemViewBinding.button.text = buttonText
        }

    }
}

