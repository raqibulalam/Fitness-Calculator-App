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

public class MaxHeartRateActivity extends AppCompatActivity {
    TextView resultTextView;
    Typeface textFont;
    RelativeLayout allScreenHeart;
    Button btnCalculate;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText inputAge;

    int inAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_heart_rate);

        //Set fonts for all activity
        allScreenHeart = (RelativeLayout) findViewById(R.id.allScreenHeart);
        textFont = Typeface.createFromAsset(getAssets(), "LobsterTwo-Italic.otf");
        GeneralMethods.setFontToAllChilds(allScreenHeart, textFont);

        resultTextView = (TextView) findViewById(R.id.resultTextView);
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

                //For Hide keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                if (radioGroup.getCheckedRadioButtonId() != -1) {
                    if (inputAge.length() != 0) {
                        inAge = Integer.parseInt(inputAge.getText().toString());
                        if (radioButton.getText().equals("Female")) {
                            resultTextView.setText(Integer.toString(218 - inAge));
                        } else {
                            resultTextView.setText(Integer.toString(220 - inAge));
                        }
                    }
                    if (inputAge.length() == 0) {
                        Toast.makeText(getBaseContext(), "Please, type correct Age.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please, select sex.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
