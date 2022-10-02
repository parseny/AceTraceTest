package hse.ru.colorpicker

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nvt.color.ColorPickerDialog
import hse.ru.colorpicker.databinding.ActivityColorPickerBinding

class ColorPickerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorPickerBinding
    lateinit var optButton: Button
    lateinit var colorShowButton: Button
    private lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityColorPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        optButton = binding.optbutton
        colorShowButton = binding.colorshow
        dialog = Dialog(this)
        optButton.setOnClickListener {
            dialog.setContentView(R.layout.activity_pop_up_info)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val chooseColorBtn = dialog.findViewById<Button>(R.id.choosecolor)
            val chooseTwoGradientBtn = dialog.findViewById<Button>(R.id.twogradient)
            val chooseThreeGradientBtn = dialog.findViewById<Button>(R.id.threegradient)
            chooseColorBtn.setOnClickListener {
                //Toast.makeText(this, "Color", Toast.LENGTH_SHORT).show()
                pickColor(colorShowButton, "color")

            }
            chooseTwoGradientBtn.setOnClickListener {
//                Toast.makeText(this, "Gradient", Toast.LENGTH_SHORT).show()
                Log.d("2colorbtn", "clicked")
                pickColor(colorShowButton, "gradient", 2)
            }
            chooseThreeGradientBtn.setOnClickListener {
                Log.d("3colorbtn", "clicked")
                pickColor(colorShowButton, "gradient", 3)
            }
            dialog.show()
        }
    }

    private fun pickColor(btn: Button, flag: String, numberOfColors: Int = 0) {
        when (flag) {
            "color" -> {
                val colorPicker = ColorPickerDialog(
                    this,
                    Color.BLACK, // color init
                    true, // true is show alpha
                    object : ColorPickerDialog.OnColorPickerListener {
                        override fun onCancel(dialog: ColorPickerDialog?) {
                            // handle click button Cancel
                        }

                        override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {
                            btn.setBackgroundColor(colorPicker)
                        }
                    })
                colorPicker.show()
            }
            "gradient" -> {
                val colorPicker = ColorPickerDialog(
                    this,
                    Color.BLACK,
                    true,
                    object : ColorPickerDialog.OnColorPickerListener {
                        override fun onCancel(dialog: ColorPickerDialog?) {
                            // handle click button Cancel
                        }

                        override fun onOk(dialog: ColorPickerDialog?, colorPicker: Int) {
                            val colorPicker1 = ColorPickerDialog(
                                this@ColorPickerActivity,
                                Color.BLACK,
                                true,
                                object : ColorPickerDialog.OnColorPickerListener {
                                    override fun onCancel(dialog: ColorPickerDialog?) {
                                        // handle click button Cancel
                                    }

                                    override fun onOk(
                                        dialog1: ColorPickerDialog?,
                                        colorPicker1: Int
                                    ) {
                                        if (numberOfColors == 2) {
                                            Log.d(
                                                "2 gradient colors",
                                                "$colorPicker $colorPicker1"
                                            )
                                            val gradient = GradientDrawable(
                                                GradientDrawable.Orientation.LEFT_RIGHT,
                                                intArrayOf(colorPicker, colorPicker1)
                                            );
                                            Log.d("got 3gradient", gradient.toString())
                                            gradient.cornerRadius = 10f;
                                            btn.background = gradient
                                        } else {
                                            val colorPicker2 = ColorPickerDialog(
                                                this@ColorPickerActivity,
                                                Color.BLACK,
                                                true,
                                                object : ColorPickerDialog.OnColorPickerListener {
                                                    override fun onCancel(dialog: ColorPickerDialog?) {
                                                        // handle click button Cancel
                                                    }

                                                    override fun onOk(
                                                        dialog2: ColorPickerDialog?,
                                                        colorPicker2: Int
                                                    ) {
                                                        Log.d(
                                                            "3 gradient colors",
                                                            "$colorPicker $colorPicker1 $colorPicker2"
                                                        )
                                                        val gradient = GradientDrawable(
                                                            GradientDrawable.Orientation.LEFT_RIGHT,
                                                            intArrayOf(
                                                                colorPicker,
                                                                colorPicker1,
                                                                colorPicker2
                                                            )
                                                        );
                                                        Log.d("got 3gradient", gradient.toString())
                                                        gradient.cornerRadius = 10f;
                                                        btn.background = gradient
                                                    }
                                                })
                                            colorPicker2.show()
                                        }
                                    }
                                })
                            colorPicker1.show()
                        }
                    })
                colorPicker.show()
            }
        }
    }
}
