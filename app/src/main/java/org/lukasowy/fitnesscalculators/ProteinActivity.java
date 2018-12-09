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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProteinActivity extends AppCompatActivity {
    TextView resultTextView;
    Typeface textFont;
    RelativeLayout allScreenProtein;
    Spinner spinnerGoal;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnCalculate;
    EditText inputWeight, inputAge;

    double posGoal, inAge, inWeight, ans;
    String itemGoal, sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein);


        //Set fonts for all activity
        allScreenProtein = (RelativeLayout) findViewById(R.id.allScreenProtein);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenProtein, textFont);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
        onSelectedListenerSpinners();
        onClickListenerButton();
    }

    private void onClickListenerButton() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                inputAge = (EditText) findViewById(R.id.inputAge);
                inputWeight = (EditText) findViewById(R.id.inputWeight);

                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    if (inputAge.length() != 0 && inputWeight.length() != 0) {
                        inAge = Double.parseDouble(inputAge.getText().toString());
                        inWeight = Double.parseDouble(inputWeight.getText().toString());

                        sex = (String) radioButton.getText();

                        ans = calculateProteinIntake(inWeight, inAge, sex);
                        resultTextView.setText(Double.toString(ans));

                    } else if (inputAge.length() == 0 && inputWeight.length() > 0) {
                        Toast.makeText(getBaseContext(), "Please, type Age.", Toast.LENGTH_SHORT).show();
                    } else if (inputAge.length() > 0 && inputWeight.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type Weight.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), "Please, type Weight and Age.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please, select sex.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void onSelectedListenerSpinners() {
        //Find the spinner
        spinnerGoal = (Spinner) findViewById(R.id.spinnerGoal);

        //Set adapter to spinner
        ArrayAdapter<CharSequence> adapterActivity = ArrayAdapter.createFromResource(this, R.array.itemsSpGoalProtein, android.R.layout.simple_list_item_activated_1);
        spinnerGoal.setAdapter(adapterActivity);

        //For spinner
        spinnerGoal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemGoal = adapterView.getItemAtPosition(i).toString();
                if (i == 0) {
                    posGoal = 1;
                } else if (i == 1) {
                    posGoal = 1.046;
                } else {
                    posGoal = 1.194;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public double calculateProteinIntake(double inWeight, double inAge, String sex) {
        if (sex.equals("Female")) {

            return 10;

        } else {
            return 1;
        }
    }
}
