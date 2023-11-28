package com.NguyenNam.logbook;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    // Declare buttons for digits, operators, and other UI elements
    public Button buttonNumberOne, buttonNumberTwo, buttonNumberThree, buttonNumberFour, buttonNumberFive, button6, buttonNumberSeven, buttonNumberEight, buttonNumberNine, buttonNumberZero, butonAdd, butonSub, butonMultiply, butonDive, butonEqual, butonC;

    // Declare EditText for displaying and entering values
    private EditText editText_Value;

    // Declare TextView for displaying the calculation history
    private TextView til_Value;

    // Declare variables to store operand values and flags for selected operator
    private float value_1, value_2;
    private boolean isAdd, isSub, isMul, isDiv, isHasValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calulator_layout);

        // Initialize UI elements (buttons, EditText, TextView)
        initializeButtons();

        // Set OnClickListener for digit buttons
        setDigitButtonClickListeners();

        // Set OnClickListener for operator buttons
        setOperatorButtonClickListeners();

        // Set OnClickListener for equals button and clear button
        setEqualButtonClickListener();
    }

    // Method to initialize UI elements
    private void initializeButtons() {
        // Initialize buttons and UI elements by finding their IDs
        // (button0, button1, ..., buttonEqual, etc.)
        buttonNumberZero = findViewById(R.id.button0);
        buttonNumberOne = findViewById(R.id.button1);
        buttonNumberTwo = findViewById(R.id.button2);
        buttonNumberThree = findViewById(R.id.button3);
        buttonNumberFour = findViewById(R.id.button4);
        buttonNumberFive = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        buttonNumberSeven = findViewById(R.id.button7);
        buttonNumberEight = findViewById(R.id.button8);
        buttonNumberNine = findViewById(R.id.button9);
        butonAdd = findViewById(R.id.buttonAdd);
        butonSub = findViewById(R.id.buttonSubtract);
        butonMultiply = findViewById(R.id.buttonMultiply);
        butonDive = findViewById(R.id.buttonDivide);
        butonEqual = findViewById(R.id.buttonEqual);
        butonC = findViewById(R.id.buttonC);

        // Initialize EditText and TextView
        editText_Value = findViewById(R.id.editTextValue);
        til_Value = findViewById(R.id.tilValue);
    }

    // Method to set OnClickListener for digit buttons (0-9)
    private void setDigitButtonClickListeners() {
        // Add OnClickListener for digit buttons (0-9)
        setDigitButtonClickListener(buttonNumberZero, "0");
        setDigitButtonClickListener(buttonNumberOne, "1");
        setDigitButtonClickListener(buttonNumberTwo, "2");
        setDigitButtonClickListener(buttonNumberThree, "3");
        setDigitButtonClickListener(buttonNumberFour, "4");
        setDigitButtonClickListener(buttonNumberFive, "5");
        setDigitButtonClickListener(button6, "6");
        setDigitButtonClickListener(buttonNumberSeven, "7");
        setDigitButtonClickListener(buttonNumberEight, "8");
        setDigitButtonClickListener(buttonNumberNine, "9");
    }

    // Method to set OnClickListener for operator buttons (+, -, *, /)
    private void setOperatorButtonClickListeners() {
        // Add OnClickListener for operator buttons (+, -, *, /)
        setOperatorButtonClickListener(butonAdd, "+");
        setOperatorButtonClickListener(butonSub, "-");
        setOperatorButtonClickListener(butonMultiply, "*");
        setOperatorButtonClickListener(butonDive, "/");
    }

    // Method to set OnClickListener for equals button and clear button
    private void setEqualButtonClickListener() {
        // Add OnClickListener for the equals button
        butonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEqualButtonClick();
            }
        });

        // Add OnClickListener for the clear button
        butonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_Value.setText("0");
                til_Value.setText(null);
                isHasValue = false;
            }
        });
    }

    // Method to set OnClickListener for digit buttons
    private void setDigitButtonClickListener(Button button, final String digit) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDigitButtonClick(digit);
            }
        });
    }

    // Method to set OnClickListener for operator buttons
    private void setOperatorButtonClickListener(Button button, final String operator) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorButtonClick(operator);
            }
        });
    }

    // Method to handle digit button click
    private void handleDigitButtonClick(String digit) {
        if (!isHasValue) {
            editText_Value.setText("");
            isHasValue = true;
        }
        editText_Value.append(digit);
    }

    // Method to handle operator button click
    private void handleOperatorButtonClick(String operator) {
        if (!isAdd && !isDiv && !isMul && !isSub) {
            // If no operator is selected, set the first operand and operator
            value_1 = Float.parseFloat(editText_Value.getText() + "");
            isHasValue = false;
            til_Value.setText(value_1 + " " + operator + " ");
            setOperatorFlag(operator);
        } else {
            // If an operator is already selected, set the second operand and perform calculation
            value_2 = Float.parseFloat(editText_Value.getText() + "");
            performCalculation();
            til_Value.append(value_2 + " " + operator + " ");
            setOperatorFlag(operator);
        }
    }

    // Method to handle equals button click
    private void handleEqualButtonClick() {
        if (isAdd || isSub || isMul || isDiv) {
            // If an operator is selected, set the second operand and perform calculation
            value_2 = Float.parseFloat(editText_Value.getText() + "");
            performCalculation();
            til_Value.append(value_2 + " = ");
            resetFlags();
            isHasValue = false;
        }
    }

    // Method to set the selected operator flag
    private void setOperatorFlag(String operator) {
        resetFlags();
        isHasValue = false;
        switch (operator) {
            case "+":
                isAdd = true;
                break;
            case "-":
                isSub = true;
                break;
            case "*":
                isMul = true;
                break;
            case "/":
                isDiv = true;
                break;
        }
    }

    // Method to perform the calculation based on the selected operator
    private void performCalculation() {
        if (isAdd) {
            editText_Value.setText(value_1 + value_2 + "");
            value_1 = Float.parseFloat(editText_Value.getText() + "");
        } else if (isSub) {
            editText_Value.setText(value_1 - value_2 + "");
            value_1 = Float.parseFloat(editText_Value.getText() + "");
        } else if (isMul) {
            editText_Value.setText(value_1 * value_2 + "");
            value_1 = Float.parseFloat(editText_Value.getText() + "");
        } else if (isDiv) {
            if (value_2 != 0) {
                editText_Value.setText(value_1 / value_2 + "");
                value_1 = Float.parseFloat(editText_Value.getText() + "");
            } else {
                // Handle division by zero error
                editText_Value.setText("Error");
            }
        }
    }

    // Method to reset operator flags
    private void resetFlags() {
        isAdd = false;
        isSub = false;
        isMul = false;
        isDiv = false;
    }
}
