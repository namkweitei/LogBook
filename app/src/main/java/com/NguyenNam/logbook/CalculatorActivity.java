package com.NguyenNam.logbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CalculatorActivity extends AppCompatActivity {
    public Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,buttonAdd,buttonSub,buttonMultiply,buttonDive,buttonEqual,buttonC;
    private EditText editTextValue;
    private TextView tilValue;
    private float value1, value2;
    private boolean isAdd, isSub, isMul, isDiv, isHasValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calulator_layout);
        // Initialize your buttons here
        initializeButtons();

        // Set OnClickListener for digit buttons
        setDigitButtonClickListeners();

        // Set OnClickListener for operator buttons
        setOperatorButtonClickListeners();

        // Set OnClickListener for equals button
        setEqualButtonClickListener();
    }
    private void initializeButtons() {
        // Initialize your buttons (button0, button1, ..., buttonEqual, etc.)
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSub = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDive = findViewById(R.id.buttonDivide);
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonC = findViewById(R.id.buttonC);

        editTextValue = findViewById(R.id.editTextValue);
        tilValue = findViewById(R.id.tilValue);
    }
    private void setDigitButtonClickListeners() {
        // Add OnClickListener for digit buttons (0-9)
        setDigitButtonClickListener(button0, "0");
        setDigitButtonClickListener(button1, "1");
        setDigitButtonClickListener(button2, "2");
        setDigitButtonClickListener(button3, "3");
        setDigitButtonClickListener(button4, "4");
        setDigitButtonClickListener(button5, "5");
        setDigitButtonClickListener(button6, "6");
        setDigitButtonClickListener(button7, "7");
        setDigitButtonClickListener(button8, "8");
        setDigitButtonClickListener(button9, "9");
        // Repeat for other digit buttons
    }

    private void setOperatorButtonClickListeners() {
        // Add OnClickListener for operator buttons (+, -, *, /)
        setOperatorButtonClickListener(buttonAdd, "+");
        setOperatorButtonClickListener(buttonSub, "-");
        setOperatorButtonClickListener(buttonMultiply, "*");
        setOperatorButtonClickListener(buttonDive, "/");
        // Repeat for other operator buttons
    }

    private void setEqualButtonClickListener() {
        // Add OnClickListener for the equals button
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleEqualButtonClick();
            }
        });
    }

    private void setDigitButtonClickListener(Button button, final String digit) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDigitButtonClick(digit);
            }
        });
    }

    private void setOperatorButtonClickListener(Button button, final String operator) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleOperatorButtonClick(operator);
            }
        });
    }

    private void handleDigitButtonClick(String digit) {
        if (!isHasValue) {
            editTextValue.setText("");
            isHasValue = true;
        }
        editTextValue.append(digit);
    }

    private void handleOperatorButtonClick(String operator) {
        if (!isAdd && !isDiv && !isMul && !isSub) {
            value1 = Float.parseFloat(editTextValue.getText() + "");
            isHasValue = false;
            tilValue.setText(value1 + " " + operator + " ");
            setOperatorFlag(operator);
        } else {
            value2 = Float.parseFloat(editTextValue.getText() + "");
            performCalculation();
            tilValue.append(value2 + " " + operator + " ");
            setOperatorFlag(operator);
        }
    }

    private void handleEqualButtonClick() {
        if (isAdd || isSub || isMul || isDiv) {
            value2 = Float.parseFloat(editTextValue.getText() + "");
            performCalculation();
            tilValue.append(value2 + " = ");
            resetFlags();
            isHasValue = false;
        }
    }

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

    private void performCalculation() {
        if (isAdd) {
            editTextValue.setText(value1 + value2 + "");
            value1 = Float.parseFloat(editTextValue.getText() + "");
        } else if (isSub) {
            editTextValue.setText(value1 - value2 + "");
            value1 = Float.parseFloat(editTextValue.getText() + "");
        } else if (isMul) {
            editTextValue.setText(value1 * value2 + "");
            value1 = Float.parseFloat(editTextValue.getText() + "");
        } else if (isDiv) {
            if (value2 != 0) {
                editTextValue.setText(value1 / value2 + "");
                value1 = Float.parseFloat(editTextValue.getText() + "");
            } else {
                // Handle division by zero error
                editTextValue.setText("Error");
            }
        }
    }

    private void resetFlags() {
        isAdd = false;
        isSub = false;
        isMul = false;
        isDiv = false;
    }
}
