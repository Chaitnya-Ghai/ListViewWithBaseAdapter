package cg.tutorials.listviewwithbaseadapter

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cg.tutorials.listviewwithbaseadapter.databinding.ActivityMainBinding
import cg.tutorials.listviewwithbaseadapter.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   private var list = arrayListOf("C","C++","kotlin")
    private var listAdapter = ListAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.listview.adapter = listAdapter
        binding.fab.setOnClickListener {
           val dialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                setCancelable(false)
                window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
                show()
            }
            dialogBinding.addBtn.setOnClickListener{
                if (dialogBinding.etDialog.text.trim().toString().isEmpty()){
                    dialogBinding.etDialog.error="Enter value"
                }
                else{
                    list.add(dialogBinding.etDialog.text.trim().toString())
                    listAdapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
            }
            dialogBinding.cancelButton.setOnClickListener{
                dialog.dismiss()
            }
        }
    }
}