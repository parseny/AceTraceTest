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
            val chooseGradientBtn = dialog.findViewById<Button>(R.id.choosegradient)
            chooseColorBtn.setOnClickListener {
                //Toast.makeText(this, "Color", Toast.LENGTH_SHORT).show()
                pickColor(colorShowButton, "color")

            }
            chooseGradientBtn.setOnClickListener {
//                Toast.makeText(this, "Gradient", Toast.LENGTH_SHORT).show()
                pickColor(colorShowButton, "gradient")
            }
            dialog.show()
        }
    }

    private fun pickColor(btn: Button, flag: String) {
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
                                        val gd = GradientDrawable(
                                            GradientDrawable.Orientation.LEFT_RIGHT,
                                            intArrayOf(colorPicker, colorPicker1)
                                        );
                                        gd.cornerRadius = 10f;
                                        btn.background = gd

                                       Log.d("-----", colorPicker.toString() + " " + colorPicker1.toString())
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
