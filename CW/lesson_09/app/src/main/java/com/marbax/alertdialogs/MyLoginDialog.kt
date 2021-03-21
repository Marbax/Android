package com.marbax.alertdialogs

import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

open class MyLoginDialog(
    context: AppCompatActivity,
    listener: View.OnClickListener?,
    login: String = "",
    password: String = ""
) {
    private val _context: AppCompatActivity = context
    private val _listener: View.OnClickListener? = listener

    private var _login: EditText
    val userLogin: String
        get() = _login.text.toString()

    private var _password: EditText
    val userPassword: String
        get() = _password.text.toString()

    private var _btnConfirm: Button

    private var _dialog: AlertDialog

    init {
        _context.apply {
            val view = layoutInflater.inflate(R.layout.dialog_view, null, false)
            _btnConfirm = view.findViewById<Button>(R.id.btnConfirm)
            _btnConfirm.setOnClickListener(_listener)
            _login = view.findViewById<EditText>(R.id.etName)
            _login.setText(login)
            _password = view.findViewById<EditText>(R.id.etPass)
            _password.setText(password)
            _login.doOnTextChanged { text, start, before, count ->
                onLoginChanged(
                    text,
                    start,
                    before,
                    count
                )
            }
            _password.doOnTextChanged { text, start, before, count ->
                onPasswordChanged(
                    text,
                    start,
                    before,
                    count
                )
            }
            val builder = AlertDialog.Builder(this)
                .setView(view)
            _dialog = builder.create()
        }
    }

    open fun onLoginChanged(text: CharSequence?, start: Int, before: Int, count: Int) {

    }

    open fun onPasswordChanged(text: CharSequence?, start: Int, before: Int, count: Int) {

    }

    fun setButton(text: CharSequence, listener: () -> Unit) {
        _btnConfirm.text = text
        _btnConfirm.setOnClickListener { listener.invoke() }
    }

    fun show() = _dialog.show()
}