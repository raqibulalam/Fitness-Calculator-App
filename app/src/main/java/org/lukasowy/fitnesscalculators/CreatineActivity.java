package org.lukasowy.fitnesscalculators;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CreatineActivity extends AppCompatActivity {
    TextView resultTextView1, resultTextView2;
    Typeface textFont;
    RelativeLayout allScreenCreatine;
    Button btnCalculate;
    EditText inputWeight;

    int inWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creatine);

        //Set fonts for all activity
        allScreenCreatine = (RelativeLayout) findViewById(R.id.allScreenCreatine);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenCreatine, textFont);

        resultTextView1 = (TextView) findViewById(R.id.resultTextView1);
        resultTextView2 = (TextView) findViewById(R.id.resultTextView2);

        onClickListenerButton();
    }

    private void onClickListenerButton() {
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputWeight = (EditText) findViewById(R.id.inputWeight);

                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                calculateCreatineIntake();
            }
        });
    }

    private void calculateCreatineIntake() {
        String loadDosage = "Loading Dosage ";
        String maninDosage = "Maintenance Dosage ";
        int loadDosageValue = 0, maninDosageValue = 0;

        if (inputWeight.length() != 0) {
            inWeight = Integer.parseInt(inputWeight.getText().toString());
            if (inWeight > 160) {
                Toast.makeText(getBaseContext(), "Please, type correct Weight.", Toast.LENGTH_SHORT).show();
            } else {
                if (isBetween(inWeight, 1, 70)) {
                    loadDosageValue = 14;
                    maninDosageValue = 6;
                } else if (isBetween(inWeight, 71, 79)) {
                    loadDosageValue = 15;
                    maninDosageValue = 7;
                } else if (isBetween(inWeight, 80, 90)) {
                    loadDosageValue = 16;
                    maninDosageValue = 8;
                } else if (isBetween(inWeight, 91, 102)) {
                    loadDosageValue = 17;
                    maninDosageValue = 9;
                } else {
                    loadDosageValue = 18;
                    maninDosageValue = 10;
                }
                resultTextView1.setText(loadDosage + Integer.toString(loadDosageValue) + " g/day");
                resultTextView2.setText(maninDosage + Integer.toString(maninDosageValue) + " g/day");
            }
        } else {
            Toast.makeText(getBaseContext(), "Please, type correct Weight.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
