package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    private var tv_screen : TextView?=null
    private var lastnumeric : Boolean=false
    private var lastdot : Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_screen=findViewById(R.id.screen_input)
        }
    fun digit(view: View){
        tv_screen?.append((view as Button).text)
        lastnumeric=true
        lastdot=false
    }
    fun clear(view: View){
        tv_screen?.text=""
    }
    fun decimal(view: View){
        if(lastnumeric && !lastdot){
            tv_screen?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    fun operator(view: View){
        tv_screen?.text?.let {
            if(lastnumeric && !isoperator(it.toString())){
                tv_screen?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }
    private fun isoperator(value :String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("+") || value.contains("-") || value.contains("/")
                    || value.contains("*")
        }
    }
    fun equal(view: View){
        if(lastnumeric){
            var tv_value=tv_screen?.text.toString()
            var prefix=" "
            try{
                if(tv_value.startsWith("-")){
                    prefix="-"
                    tv_value=tv_value.substring(1)
                }
                if(tv_value.contains("-")){
                    var split_value=tv_value.split("-")
                    var one=split_value[0]
                    var two=split_value[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tv_screen?.text=(one.toDouble()-two.toDouble()).toString()
                }
                else if(tv_value.contains("+")){
                    var split_value=tv_value.split("+")
                    var one=split_value[0]
                    var two=split_value[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tv_screen?.text=(one.toDouble()+two.toDouble()).toString()
                }
                else if(tv_value.contains("*")){
                    var split_value=tv_value.split("*")
                    var one=split_value[0]
                    var two=split_value[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tv_screen?.text=(one.toDouble()*two.toDouble()).toString()
                }
                else if(tv_value.contains("/")){
                    var split_value=tv_value.split("/")
                    var one=split_value[0]
                    var two=split_value[1]
                    if(prefix.isNotEmpty()){
                        one=prefix+one
                    }
                    tv_screen?.text=(one.toDouble()/two.toDouble()).toString()
                }
            }catch(e: java.lang.ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}