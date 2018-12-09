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

public class MetabolicActivity extends AppCompatActivity {

    TextView resultTextView;
    Typeface textFont;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnCalculate;
    EditText inputHeight, inputWeight, inputAge;
    RelativeLayout allScreenBMR;

    double inHeight, inWeight, result, bmr;
    int inAge;
    String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metabolic);

        //Set fonts for all activity
        allScreenBMR = (RelativeLayout) findViewById(R.id.allScreenBMR);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenBMR, textFont);

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
                inputAge = (EditText) findViewById(R.id.inputAge);
                sex = null;

                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);


                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    if (inputHeight.length() != 0 && inputWeight.length() != 0 && inputAge.length() != 0) {

                        inHeight = Double.parseDouble(inputHeight.getText().toString());
                        inWeight = Double.parseDouble(inputWeight.getText().toString());
                        inAge = Integer.parseInt(inputAge.getText().toString());
                        sex = radioButton.getText().toString();

                        bmr = calculateBMR(inWeight, inHeight, inAge);
                        result = calculateBMRbaseOnSex(sex, bmr);
                        resultTextView.setText(Double.toString(result));

                    }
                    if (inputHeight.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Height.", Toast.LENGTH_SHORT).show();
                    }
                    if (inputWeight.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Weight.", Toast.LENGTH_SHORT).show();
                    }
                    if (inputAge.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Age.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please, select sex.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public double calculateBMRbaseOnSex(String sex, double bmr) {

        return (sex.equals("Male")) ? bmr + 5 : bmr - 161;

    }

    public double calculateBMR(double inWeight, double inHeight, int inAge) {

        return 10 * inWeight + 6.25 * inHeight - 5 * (double)inAge;
    }
}
