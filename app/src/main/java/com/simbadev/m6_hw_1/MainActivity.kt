package com.simbadev.m6_hw_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.simbadev.m6_hw_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListener()
    }

    private fun initClickListener() {
        binding.btnSend.setOnClickListener {
            val inputEditText = binding.etTitle.text.toString().trim()
            if (inputEditText.isNotEmpty()) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(REQUEST_CODE, inputEditText)
                startForResult.launch(intent)
            } else {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.edittext),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val text = data?.getStringExtra(REQUEST_CODE)
                binding.etTitle.setText(text)
            }
        }

    companion object{
        const val REQUEST_CODE = "title"
    }
}