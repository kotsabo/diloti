package com.example.kotsabo.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.view.ViewGroup
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private var totalPoints: Int = 11
    private var activity: Activity = this

    private val game = Game()
    private val adapter = CustomAdapter(game)

    private lateinit var recyclerView: RecyclerView

    private lateinit var button: Button
    private lateinit var checkBox: CheckBox
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var editText4: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recyclerView = findViewById(R.id.recyclerView)
        this.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        this.recyclerView.adapter = adapter

        this.button = findViewById(R.id.button)
        this.button.setOnClickListener { goAction() }

        this.checkBox = findViewById(R.id.checkBox)

        this.editText1 = findViewById(R.id.editText)
        this.editText2 = findViewById(R.id.editText2)
        this.editText3 = findViewById(R.id.editText3)
        this.editText4 = findViewById(R.id.editText4)

        setupUI(findViewById(R.id.parent))

        val generalTextWatcher = object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if ((editText1.text.hashCode() == s.hashCode()) && (editText1.hasFocus())) {
                    autoComplete(editText1, editText2)
                } else if ((editText2.text.hashCode() == s.hashCode()) && (editText2.hasFocus())) {
                    autoComplete(editText2, editText1)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

            override fun afterTextChanged(s: Editable) { }

        }

        this.editText1.addTextChangedListener(generalTextWatcher)
        this.editText2.addTextChangedListener(generalTextWatcher)

        this.checkBox.setOnClickListener {
            this.totalPoints = if (this.checkBox.isChecked) {
                7
            } else {
                11
            }
        }

    }

    private fun setupUI(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if ((view !is EditText) && (view !is Button)) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard(activity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView)
            }
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
    }

    private fun autoComplete(editText1: EditText, editText2: EditText) {
        try {
            val points1: Int = editText1.text.toString().toInt()
            if (points1 in 0..this.totalPoints) {
                val points2: Int = this.totalPoints - points1
                editText2.setText(points2.toString())
            } else {
                resetEditTexts()
                Toast.makeText(applicationContext, "wrong", Toast.LENGTH_SHORT).show()
            }
        } catch (e: NumberFormatException) {
            Log.e("error", "wrong input")
        }
    }

    private fun goAction() {
        // Code here executes on main thread after user presses button
        try {
            val points1: Int = this.editText1.text.toString().toInt()
            val points2: Int = this.editText2.text.toString().toInt()
            var multiplier1 = 0
            if (this.editText4.text.toString() != "") {
                multiplier1 = this.editText4.text.toString().toInt()
            }
            var multiplier2 = 0
            if (this.editText3.text.toString() != "") {
                multiplier2 = this.editText3.text.toString().toInt()
            }

            this.game.updateGame(points1, points2, multiplier1, multiplier2)
            this.recyclerView.adapter = this.adapter
            this.recyclerView.scrollToPosition(adapter.itemCount - 1)

            resetEditTexts()
        } catch (e: NumberFormatException) {
            Log.e("error", "wrong input")
        }

    }

    private fun resetEditTexts() {
        this.editText1.setText("")
        this.editText2.setText("")
        this.editText3.setText("")
        this.editText4.setText("")
    }

}


