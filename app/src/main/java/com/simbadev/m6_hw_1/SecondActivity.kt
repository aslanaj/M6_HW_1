package com.simbadev.m6_hw_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.simbadev.m6_hw_1.databinding.ActivitySecondAvtivityBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondAvtivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondAvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = intent.getStringExtra(REQUEST_CODE)
        binding.etSecondTitle.setText(text)

        initClickListener()
    }

    private fun initClickListener() {
        binding.btnSendBack.setOnClickListener {
            val inputEditText = binding.etSecondTitle.text.toString().trim()
            val intent = Intent()
            if (inputEditText.isNotEmpty()) {
                intent.putExtra(REQUEST_CODE, inputEditText)
                val returnIntent = Intent()
                returnIntent.putExtra(REQUEST_CODE, inputEditText)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            } else {
                Toast.makeText(
                    this@SecondActivity,
                    getString(R.string.edittext),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    companion object {
        const val REQUEST_CODE = "title"
    }
}