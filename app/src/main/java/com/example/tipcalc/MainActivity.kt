package com.example.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountInput: EditText
    private lateinit var tipPercentInput: EditText
    private lateinit var peopleCountInput: EditText
    private lateinit var tipTotalText: TextView
    private lateinit var grandTotalText: TextView
    private lateinit var perPersonText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Create the root layout container (Vertical Stack)
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            // Reference padding from the dimens.xml resource file
            setPadding(resources.getDimensionPixelSize(R.dimen.activity_padding)) 
        }

        // 2. Create the UI Input Elements

        // Bill Amount Input
        billAmountInput = createEditText("Bill Amount (e.g., 50.00)", InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
        container.addView(billAmountInput)

        // Tip Percentage Input
        tipPercentInput = createEditText("Tip Percentage (e.g., 15)", InputType.TYPE_CLASS_NUMBER)
        container.addView(tipPercentInput)

        // Number of People Input
        peopleCountInput = createEditText("Split with (Number of people)", InputType.TYPE_CLASS_NUMBER)
        container.addView(peopleCountInput)

        // 3. Create the Calculate Button
        val calculateButton = Button(this).apply {
            text = "Calculate Tip"
            // Set the listener to run the calculation logic
            setOnClickListener { calculateAndDisplay() }
        }
        container.addView(calculateButton)

        // 4. Create the Output TextViews
        tipTotalText = createTextView("Total Tip: \$0.00")
        container.addView(tipTotalText)

        grandTotalText = createTextView("Grand Total: \$0.00")
        container.addView(grandTotalText)

        perPersonText = createTextView("Each Person Pays: \$0.00")
        container.addView(perPersonText)

        // Set the main layout to the activity
        setContentView(container)
    }

    /**
     * Helper function to create standardized TextViews.
     */
    private fun createTextView(defaultText: String): TextView {
        return TextView(this).apply {
            text = defaultText
            textSize = 18f 
            setPadding(0, 16, 0, 16) 
        }
    }

    /**
     * Helper function to create standardized EditTexts.
     */
    private fun createEditText(hintText: String, inputType: Int): EditText {
        return EditText(this).apply {
            hint = hintText
            inputType = inputType
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setPadding(16)
        }
    }

    /**
     * The core logic for calculating and displaying the tip.
     */
    private fun calculateAndDisplay() {
        try {
            // Read and convert inputs
            val billAmount = billAmountInput.text.toString().toDouble()
            val tipPercent = tipPercentInput.text.toString().toDouble()
            val peopleCount = peopleCountInput.text.toString().toInt()

            // Input Validation
            if (billAmount <= 0 || peopleCount <= 0) {
                tipTotalText.text = "Error: Bill and people count must be positive."
                grandTotalText.text = ""
                perPersonText.text = ""
                return
            }

            // Calculation Logic
            val tipTotal = billAmount * (tipPercent / 100.0)
            val grandTotal = billAmount + tipTotal
            val perPersonAmount = grandTotal / peopleCount

            // Format numbers for display
            val currencyFormat = NumberFormat.getCurrencyInstance()

            // Update the TextViews with the results
            tipTotalText.text = "Total Tip: ${currencyFormat.format(tipTotal)}"
            grandTotalText.text = "Grand Total: ${currencyFormat.format(grandTotal)}"
            perPersonText.text = "Each Person Pays: ${currencyFormat.format(perPersonAmount)}"

        } catch (e: NumberFormatException) {
            tipTotalText.text = "Error: Please enter valid numbers in all fields."
            grandTotalText.text = ""
            perPersonText.text = ""
        }
    }
}

