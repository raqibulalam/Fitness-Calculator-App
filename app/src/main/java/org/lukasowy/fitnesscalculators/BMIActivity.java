package org.lukasowy.fitnesscalculators;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BMIActivity extends AppCompatActivity {

    TextView resultTextView;
    Typeface textFont;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnCalculate;
    EditText inputHeight, inputWeight;
    RelativeLayout allScreenBMI;

    double inHeight, inWeight, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        //Set fonts for all activity
        allScreenBMI = (RelativeLayout) findViewById(R.id.allScreenBMI);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenBMI, textFont);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        onClickListenerButton();

    }

    public void onClickListenerButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                inputHeight = (EditText) findViewById(R.id.inputHeight);
                inputWeight = (EditText) findViewById(R.id.inputWeight);

                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    if (inputHeight.length() != 0 && inputWeight.length() != 0) {

                        inHeight = Double.parseDouble(inputHeight.getText().toString());
                        inWeight = Double.parseDouble(inputWeight.getText().toString());

                        result = calculateBMI(inWeight, inHeight);
                        String message = BMICategorization(result);
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                        resultTextView.setText(Double.toString(result));

                    }
                    if (inputHeight.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Height.", Toast.LENGTH_SHORT).show();
                    }
                    if (inputWeight.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Weight.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please, select sex.", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    public double calculateBMI(double inWeight, double inHeight) {
        if (inHeight == 0 || inWeight == 0) {
            return 0;
        }
        return GeneralMethods.round(inWeight / ((inHeight / 100) * (inHeight / 100)), 2);
    }

    public String BMICategorization(double ans) {
        if (isBetween(ans, 0, 15)) {
            return "Very severely underweight";
        } else if (isBetween(ans, 15, 16)) {
            return "Severely underweight";
        } else if (isBetween(ans, 16, 18.5)) {
            return "Underweight";
        } else if (isBetween(ans, 18.5, 25)) {
            return "Normal (healthy weight)";
        } else if (isBetween(ans, 25, 30)) {
            return "Overweight";
        } else if (isBetween(ans, 30, 35)) {
            return "Moderately obese";
        } else if (isBetween(ans, 35, 40)) {
            return "Severely obese";
        } else if (isBetween(ans, 40, 55)) {
            return "Very severely obese";
        } else {
            return "Please, check the correctness of the input data";
        }
    }

    private boolean isBetween(double x, double lower, double upper) {
        return lower < x && x <= upper;
    }
}
