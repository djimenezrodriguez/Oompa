package com.napptilus.oompaloompa.Utils

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.napptilus.oompaloompa.R
import kotlinx.android.synthetic.main.toolbar_oompa.view.*

class ToolbarOompa(context: Context) : LinearLayout(context) {


    init {
        inflate(context, R.layout.toolbar_oompa, this)
    }
    fun setActionToBackButton(actionClickBack: OnClickListener){
        tlbr_return_button.setOnClickListener(actionClickBack)
    }
    fun setActionToHomeButton(actionClickHome: OnClickListener){
        tlbr_home_button.setOnClickListener(actionClickHome)
    }
    fun setTextToolbar(textToolbar: String){
        tlbr_title.setText(textToolbar)
    }

}