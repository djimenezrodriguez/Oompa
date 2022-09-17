package com.napptilus.oompaloompa.presentation.WorkersActivity.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.napptilus.oompaloompa.R
import kotlinx.android.synthetic.main.activity_workers.*

class WorkersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workers)
        Oompa_toolbar.setActionToBackButton(View.OnClickListener { onBackPressed() })
        Oompa_toolbar.setTextToolbar(resources.getString(R.string.title_Oompa))
    }
}