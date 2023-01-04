package com.example.shoppinglist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

class AddShoppingItemDialog(
    context: Context,
    var addDialogListener: AddDialogListener
): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_shopping_item)

        tv_add.setOnClickListener {
            val name = et_name_dialog.text.toString()
            val amount = et_amount_dialog.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please Enter all the information.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        tv_cancel.setOnClickListener {
            cancel()
        }
    }
}