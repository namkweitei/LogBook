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
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "0");}
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isHasValue){
                    editTextValue.setText(null);
                    isHasValue = true;
                }
                editTextValue.setText(editTextValue.getText() + "9");
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd && !isDiv && !isMul && !isSub){
                    value1 = Float.parseFloat(editTextValue.getText() + "");
                    isAdd = true;
                    isHasValue = false;
                    tilValue.setText(value1 + " + ");
                }else {
                    value2 = Float.parseFloat(editTextValue.getText() + "");
                    if(isAdd){
                        editTextValue.setText(value1 + value2 + "");
                        tilValue.setText(value1 + value2 + " + ");
                        value1 = Float.parseFloat(editTextValue.getText() + "");
                        isHasValue = false;
                    }
                    if(isSub){
                        editTextValue.setText(value1 - value2 + "");
                        tilValue.setText(value1 + value2 + " - ");
                        value1 = Float.parseFloat(editTextValue.getText() + "");
                        isSub = false;
                        isAdd = true;
                        isHasValue = false;
                    }
                    if(isMul){
                        editTextValue.setText(value1 * value2 + "");
                        tilValue.setText(value1 + value2 + " * ");
                        value1 = Float.parseFloat(editTextValue.getText() + "");
                        isMul = false;
                        isAdd = true;
                        isHasValue = false;
                    }
                    if(isDiv){
                        editTextValue.setText(value1 / value2 + "");
                        tilValue.setText(value1 + value2 + " / ");
                        value1 = Float.parseFloat(editTextValue.getText() + "");
                        isDiv = false;
                        isAdd = true;
                        isHasValue = false;
                    }
                }
            }
        });
        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd && !isDiv && !isMul && !isSub){
                    value1 = Float.parseFloat(editTextValue.getText() + "");
                    isSub = true;
                    isHasValue = false;
                    tilValue.setText(value1 + " - ");
                }else {
                    value2 = Float.parseFloat(editTextValue.getText() + "");
                    if(isAdd){
                        editTextValue.setText(value1 + value2 + "");
                        tilValue.setText(value1 + value2 + " + ");
                        isAdd = false;
                        isHasValue = false;
                    }
                    if(isSub){
                        editTextValue.setText(value1 - value2 + "");
                        tilValue.setText(value1 + value2 + " - ");
                        isSub = false;
                        isHasValue = false;
                    }
                    if(isMul){
                        editTextValue.setText(value1 * value2 + "");
                        tilValue.setText(value1 + value2 + " * ");
                        isMul = false;
                        isHasValue = false;
                    }
                    if(isDiv){
                        editTextValue.setText(value1 / value2 + "");
                        tilValue.setText(value1 + value2 + " / ");
                        isDiv = false;
                        isHasValue = false;
                    }
                }
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd && !isDiv && !isMul && !isSub){
                    value1 = Float.parseFloat(editTextValue.getText() + "");
                    isMul = true;
                    isHasValue = false;
                    tilValue.setText(value1 + " * ");
                }else {
                    value2 = Float.parseFloat(editTextValue.getText() + "");
                    if(isAdd){
                        editTextValue.setText(value1 + value2 + "");
                        tilValue.setText(value1 + value2 + " + ");
                        isAdd = false;
                        isHasValue = false;
                    }
                    if(isSub){
                        editTextValue.setText(value1 - value2 + "");
                        tilValue.setText(value1 + value2 + " - ");
                        isSub = false;
                        isHasValue = false;
                    }
                    if(isMul){
                        editTextValue.setText(value1 * value2 + "");
                        tilValue.setText(value1 + value2 + " * ");
                        isMul = false;
                        isHasValue = false;
                    }
                    if(isDiv){
                        editTextValue.setText(value1 / value2 + "");
                        tilValue.setText(value1 + value2 + " / ");
                        isDiv = false;
                        isHasValue = false;
                    }
                }
            }
        });
        buttonDive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd && !isDiv && !isMul && !isSub ){
                    value1 = Float.parseFloat(editTextValue.getText() + "");
                    isDiv = true;
                    isHasValue = false;
                    tilValue.setText(value1 + " / ");
                }else {
                    value2 = Float.parseFloat(editTextValue.getText() + "");
                    if(isAdd){
                        editTextValue.setText(value1 + value2 + "");
                        tilValue.setText(value1 + value2 + " + ");
                        isAdd = false;
                        isHasValue = false;
                    }
                    if(isSub){
                        editTextValue.setText(value1 - value2 + "");
                        tilValue.setText(value1 + value2 + " - ");
                        isSub = false;
                        isHasValue = false;
                    }
                    if(isMul){
                        editTextValue.setText(value1 * value2 + "");
                        tilValue.setText(value1 + value2 + " * ");
                        isMul = false;
                        isHasValue = false;
                    }
                    if(isDiv){
                        editTextValue.setText(value1 / value2 + "");
                        tilValue.setText(value1 + value2 + " / ");
                        isDiv = false;
                        isHasValue = false;
                    }
                }
            }
        });
        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd && !isDiv && !isMul && !isSub){

                }else {
                    value2 = Float.parseFloat(editTextValue.getText() + "");
                    if(isAdd){
                        editTextValue.setText(value1 + value2 + "");
                        isAdd = false;
                    }
                    if(isSub){
                        editTextValue.setText(value1 - value2 + "");
                        isSub = false;
                    }
                    if(isMul){
                        editTextValue.setText(value1 * value2 + "");
                        isMul = false;
                    }
                    if(isDiv){
                        editTextValue.setText(value1 / value2 + "");
                        isDiv = false;
                    }
                }
            }
        });
    }

}
