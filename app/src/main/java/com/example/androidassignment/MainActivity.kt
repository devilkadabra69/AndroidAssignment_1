package com.example.androidassignment

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.input.InputManager
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.androidassignment.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var  binding:ActivityMainBinding
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //keyboard hide on keyboard select keyword in customappinputfield
        binding.custominputfield.setOnEditorActionListener { v, actionId, event ->

            if(event!=null && event.keyCode==KeyEvent.KEYCODE_ENTER ){
                val inputmethodmanager=getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
                inputmethodmanager.hideSoftInputFromWindow(binding.custominputfield.windowToken,0)
            }
            return@setOnEditorActionListener false
        }


        //keyboard hide on keyboard select keyword in appinputfield
        binding.appinputfield.setOnEditorActionListener { v, actionId, event ->

            if(event!=null && event.keyCode==KeyEvent.KEYCODE_ENTER ){
                val inputmethodmanager=getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
                inputmethodmanager.hideSoftInputFromWindow(binding.appinputfield.windowToken,0)
            }
            return@setOnEditorActionListener false
        }


        //hand-wave button click
        binding.handwavebutton.setOnClickListener {
            if(binding.appinputfield.text.isNullOrBlank()){
                binding.appinputfield.text=Editable.Factory.getInstance().newEditable("Hello")
                binding.appinputfield.extendSelection(binding.appinputfield.length())
            }else{
                val getcurrentstring_in_appinput=binding.appinputfield.text
                getcurrentstring_in_appinput.append("hello")
                binding.appinputfield.text=Editable.Factory.getInstance().newEditable(getcurrentstring_in_appinput)
                binding.appinputfield.extendSelection(binding.appinputfield.length())
            }
        }


        //complete button click
        binding.complebutton.setOnClickListener {
            val textfromcustominput=binding.custominputfield.text
            val textfromappinput=binding.appinputfield.text
            if(textfromappinput.isNotEmpty()){
                textfromappinput.append(" $textfromcustominput")
                binding.custominputfield.text=null
            }else{
                if(textfromcustominput.isNotBlank()){
                    binding.appinputfield.text=Editable.Factory.getInstance().newEditable(" $textfromcustominput")
                    binding.custominputfield.text=null
                }
            }

            val inputmethodmanager=getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
            inputmethodmanager.hideSoftInputFromWindow(binding.custominputfield.windowToken,0)
        }

    }
}

