package com.marbax.alertdialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.marbax.alertdialogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    data class MyDate(var year: Int = 0, var month: Int = 0, var dayOfMonth: Int = 0)
    data class MyTime(var hours: Int = 0, var minutes: Int = 0)
    data class AlertDialogM(
        val title: String,
        val message: String,
        val btnPositiveTxt: String,
        val btnNegativeTxt: String,
        val btnNeutralTxt: String,
        val icon: Int,
        val lambdaBtnPositive: () -> Unit,
        val lambdaBtnNegative: () -> Unit,
        val lambdaBtnNeutral: () -> Unit,
        val isCancelable: Boolean = false
    )

    private var date: MyDate = MyDate()
    private var time: MyTime = MyTime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = Calendar.getInstance()
        date.year = calendar.get(Calendar.YEAR)
        date.month = calendar.get(Calendar.MONTH)
        date.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)


        binding.apply {
            showAlert.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                    .setTitle("Alert Dialog Example")
                    .setMessage("An horrible exception.\nPress some Button")
                    .setPositiveButton("OK") { _, _ ->
                        Toast.makeText(this@MainActivity, "You pressed OK", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        Toast.makeText(this@MainActivity, "You pressed Cancel", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .setNeutralButton("Dunno") { _, _ ->
                        Toast.makeText(this@MainActivity, "You pressed Dunno", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .setIcon(R.drawable.ic_android_black_24dp)

                val dialog = builder.create()
                dialog.setCancelable(false)
                dialog.show()
            }

            showDateDialog.setOnClickListener {
                val dpd = object : DatePickerDialog(
                    this@MainActivity,
                    null,
                    date.year,
                    date.month,
                    date.dayOfMonth
                ) {
                    override fun onDateChanged(
                        view: DatePicker,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        date.year = year
                        date.month = month
                        date.dayOfMonth = dayOfMonth
                    }
                }

                dpd.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm") { _, _ ->
                    Log.d("Date Picker", date.toString())
                    Toast.makeText(this@MainActivity, date.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                dpd.show()
            }

            showTimeDialog.setOnClickListener {
                val tpd = object : TimePickerDialog(
                    this@MainActivity,
                    null,
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.HOUR_OF_DAY),
                    true
                ) {
                    override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        time.hours = hourOfDay
                        time.minutes = minute
                    }
                }

                tpd.setButton(DialogInterface.BUTTON_POSITIVE, "Confirm") { _, _ ->
                    Log.d("Time Picker", time.toString())
                    Toast.makeText(this@MainActivity, time.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
                tpd.show()
            }

            showCustomAlert.setOnClickListener {
                val view = layoutInflater.inflate(R.layout.dialog_view, null, false)
                val btnConfirm = view.findViewById<Button>(R.id.btnConfirm)
                val etName = view.findViewById<EditText>(R.id.etName)
                val etPass = view.findViewById<EditText>(R.id.etPass)
                btnConfirm.setOnClickListener {
                    if (etName.text.isNotBlank() && etPass.text.isNotBlank())
                        Toast.makeText(
                            this@MainActivity,
                            "${etName.text} ${etPass.text}",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        Toast.makeText(
                            this@MainActivity,
                            "Empty :C",
                            Toast.LENGTH_SHORT
                        ).show()
                }

                val builder = AlertDialog.Builder(this@MainActivity)
                    .setView(view)
                val dialog = builder.create()
                dialog.show()
            }

            showMyDialog.setOnClickListener {
                val mld = object : MyLoginDialog(this@MainActivity, null) {
                    override fun onLoginChanged(
                        text: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        super.onLoginChanged(text, start, before, count)
                        Toast.makeText(
                            this@MainActivity,
                            "${text} ${start} ${before} ${count}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                mld.setButton("Submit") {
                    if (mld.userLogin.isNotBlank() && mld.userPassword.isNotBlank()) {
                        Log.d("My login Dialog", "${mld.userLogin} ${mld.userPassword}")
                        Toast.makeText(
                            this@MainActivity,
                            "${mld.userLogin} ${mld.userPassword}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Log.d("My login Dialog", "Fields are empty")
                        Toast.makeText(this@MainActivity, "Fields are empty", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                mld.show()
            }

            showThroughFunc.setOnClickListener {
                val data = AlertDialogM(
                    "Func Alert",
                    "Some horrible message",
                    "Ok",
                    "CANCEL",
                    "DUNNO",
                    R.drawable.ic_android_black_24dp,
                    { ->
                        Toast.makeText(this@MainActivity, "you press OK", Toast.LENGTH_SHORT).show()
                    },
                    { ->
                        Toast.makeText(this@MainActivity, "you press CANCEL", Toast.LENGTH_SHORT)
                            .show()
                    },
                    { ->
                        Toast.makeText(this@MainActivity, "you press DUNNO", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                val dialog = createDialog(data, this@MainActivity)
                dialog.show()
            }

        }
    }

    private fun createDialog(model: AlertDialogM, context: AppCompatActivity): AlertDialog {
        model.apply {
            val builder = AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnPositiveTxt) { _, _ -> lambdaBtnPositive.invoke() }
                .setNegativeButton(btnNegativeTxt) { _, _ -> lambdaBtnNegative.invoke() }
                .setNeutralButton(btnNeutralTxt) { _, _ -> lambdaBtnNeutral.invoke() }
                .setIcon(icon)

            val dialog = builder.create()
            dialog.setCancelable(isCancelable)
            return dialog
        }
    }
}

 
