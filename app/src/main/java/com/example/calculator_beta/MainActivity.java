package com.example.calculator_beta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // Set click listener for the "=" button
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEqualsClick();
            }
        });

        // Set click listener for the "C" button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCalculator();
            }
        });

        // Set click listener for all digit and operator buttons
        for (int i = 0; i <= 9; i++) {
            int resId = getResources().getIdentifier("btn" + i, "id", getPackageName());
            Button button = findViewById(resId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonClick(((Button) v).getText().toString());
                }
            });
        }

        // Add click listeners for operator buttons like "+", "-", "*", "/"
        findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick("+");
            }
        });
        findViewById(R.id.btnMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick("-");
            }
        });
        findViewById(R.id.btnMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick("*");
            }
        });
        findViewById(R.id.btnDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick("/");
            }
        });

        // Add click listeners for other operator buttons

    }

    private void handleButtonClick(String input) {
        String currentText = editText.getText().toString();
        editText.setText(currentText + input);
    }

    private void handleEqualsClick() {
        try {
            String expression = editText.getText().toString();
            // Use ExpressionBuilder for mathematical evaluation
            Expression expressionObj = new ExpressionBuilder(expression).build();
            double result = expressionObj.evaluate();
            editText.setText(String.valueOf(result));
        } catch (Exception e) {
            // Handle any exception that may occur during evaluation
            editText.setText("Error");
        }
    }



    private void clearCalculator() {
        editText.setText("");
    }
}
