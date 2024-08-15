package com.omkar.bmicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculateButton;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();

        setupButtonClickListener();
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener((view) -> {
            double bmi = calculateBMI();
            int age = Integer.parseInt(ageEditText.toString());
            if(age >= 18){
                displayResult(bmi);
            } else {
                displayGuidance(bmi);
            }

        });
    }

    private double calculateBMI() {
        int feet = Integer.parseInt(feetEditText.getText().toString());
        int inches = Integer.parseInt(inchesEditText.getText().toString());
        int weight = Integer.parseInt(weightEditText.getText().toString());

        int totalInches = (feet * 12) + inches;

        // Height in meters, it is the inches multiplied by 0.025
        double height = totalInches * 0.0254;

        return weight / (height * height);
    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String finalTextResult;
        if(bmi < 18.5){
            finalTextResult = bmiTextResult + " - You are underweight";
        } else if (bmi > 25) {
            finalTextResult = bmiTextResult + " - You are overweight";
        } else {
            finalTextResult = bmiTextResult + " - You are healthy";
        }

        resultText.setText(finalTextResult);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String finalTextResult;
        if (radioButtonMale.isChecked()) {
            finalTextResult = bmiTextResult + "Please consult you Doctor for yor healthy range of boy, as you are under 18";
        } else if (radioButtonFemale.isChecked()) {
            finalTextResult = bmiTextResult + "Please consult you Doctor for yor healthy range of girls, as you are under 18";
        } else {
            finalTextResult = bmiTextResult + "Please consult you Doctor for yor healthy range, as you are under 18";
        }

        resultText.setText(finalTextResult);
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        radioButtonMale = findViewById(R.id.radio_button_male);
        radioButtonFemale = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }
}