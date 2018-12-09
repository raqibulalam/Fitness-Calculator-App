package org.lukasowy.fitnesscalculators;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WaterActivity extends AppCompatActivity {

      TextView resultTextView;
    Typeface textFont;
    RelativeLayout allScreenWater;
    Spinner spinnerActivity, spinnerClimate;
    Button waterCalculate;
    EditText inputWeight;

    int posActivity;
    double result, inWeight, posClimate;
    String itemActivity, itemClimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        //Set fonts for all activity
        allScreenWater = (RelativeLayout) findViewById(R.id.allScreenWater);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenWater, textFont);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        onSelectedListenerSpinners();
        onClickListenerButton();

    }

    private void onClickListenerButton() {
        waterCalculate = (Button) findViewById(R.id.waterCalculate);

        waterCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputWeight = (EditText) findViewById(R.id.inputWeight);
                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                //display result for water intake
                waterIntake();

            }
        });

    }

    //main function to calculate water intake
    private void waterIntake() {
        if (inputWeight.length() != 0) {
            //Display result from input field
            inWeight = Double.parseDouble(inputWeight.getText().toString());

            //convert           kg to lbs                                             oz to l
            result = ((((inWeight * 2.20462262) / 2) + (((posActivity * 30) / 30) * 12)) / 33.814) / posClimate;

            String str = String.format("%1.2f", result);
            result = Double.valueOf(str);
            resultTextView.setText(String.valueOf(result));
        } else {
            Toast.makeText(getBaseContext(), "Please, type Weight.", Toast.LENGTH_SHORT).show();
        }
    }

    //Important function to select item from two spinners
    private void onSelectedListenerSpinners() {

        //Find spinners
        spinnerActivity = (Spinner) findViewById(R.id.spinnerActivity);
        spinnerClimate = (Spinner) findViewById(R.id.spinnerClimate);

        //Set adapter to spinner
        ArrayAdapter<CharSequence> adapterActivity = ArrayAdapter.createFromResource(this, R.array.itemsSpActivity, android.R.layout.simple_list_item_activated_1);
        spinnerActivity.setAdapter(adapterActivity);
        ArrayAdapter<CharSequence> adapterClimate = ArrayAdapter.createFromResource(this, R.array.itemsSpClimate, android.R.layout.simple_list_item_activated_1);
        spinnerClimate.setAdapter(adapterClimate);

        //For first spinner
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemActivity = adapterView.getItemAtPosition(i).toString();
                posActivity = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //For second spinner
        spinnerClimate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemClimate = adapterView.getItemAtPosition(i).toString();
                if (i == 0) {
                    posClimate = 1;
                } else if (i == 1) {
                    posClimate = 1.12;
                } else {
                    posClimate = 1.06;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
