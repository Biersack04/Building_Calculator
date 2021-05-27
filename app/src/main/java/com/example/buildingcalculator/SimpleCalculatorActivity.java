package com.example.buildingcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import static com.example.buildingcalculator.Constants.*;

public class SimpleCalculatorActivity extends AppCompatActivity {

    MaterialTextView enterTextView;
    MaterialTextView resultsTextView;
    MaterialButton allCancel, cancel;

    Intent intent;
    String inputLine = "";
    String formula = "";
    String tempFormula = "";

    SharedPreferences sPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        layoutElementsInit();

        sPref = getSharedPreferences(APP_PREFERENCES_NAME, MODE_PRIVATE);

        String lastResult = sPref.getString(PREFERENCES_LAST_RESULT_ON_CALCULATOR," ");
        resultsTextView.setText(lastResult);

        String enterString = sPref.getString(PREFERENCES_LAST_ENTER_ON_CALCULATOR ," ");
        enterTextView.setText(enterString);
        inputLine = enterString;
    }

    private void layoutElementsInit() {

        enterTextView = findViewById(R.id.enter_text_view_simple_calculator_layout);
        resultsTextView = findViewById(R.id.result_text_view_simple_calculator_layout);

        allCancel = findViewById(R.id.all_cancel_simple_calculator);
        allCancel.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));

        cancel = findViewById(R.id.cancel_simple_calculator);
        cancel.setTypeface(Typeface.createFromAsset(getAssets(),getString(R.string.roboto_regular)));
    }

    private void setInputLine(String givenValue) {

        inputLine = inputLine + givenValue;
        enterTextView.setText(inputLine);
    }


    public void equals(View view) {

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try
        {
            result = (double) engine.eval(formula);

        } catch (ScriptException e) {

            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result != null)
        {
            resultsTextView.setText(String.valueOf(result.doubleValue()));
        }


        if (enterTextView.getText().equals(""))
        {
            Toast.makeText(this, "Empty Value", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkForPowerOf() {

        ArrayList<Integer> indexOfPowers = new ArrayList<>();

        formula = inputLine;
        tempFormula = inputLine;

        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {

        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< inputLine.length(); i++)
        {
            if(isNumeric(inputLine.charAt(i)))
                numberRight = numberRight + inputLine.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(inputLine.charAt(i)))
                numberLeft = numberLeft + inputLine.charAt(i);
            else
                break;
        }
    }

    private boolean isNumeric(char c) {

        return (c <= '9' && c >= '0') || c == '.';
    }

    public void division(View view) {

        setInputLine("/");
    }

    public void seven(View view) {

        setInputLine("7");
    }

    public void eight(View view) {

        setInputLine("8");
    }

    public void nine(View view) {

        setInputLine("9");
    }

    public void multiplication(View view) {

        setInputLine("*");
    }

    public void four(View view) {

        setInputLine("4");
    }

    public void five(View view) {

        setInputLine("5");
    }

    public void six(View view) {

        setInputLine("6");
    }

    public void minus(View view) {

        setInputLine("-");
    }

    public void one(View view) {

        setInputLine("1");
    }

    public void two(View view) {

        setInputLine("2");
    }

    public void three(View view) {

        setInputLine("3");
    }

    public void plus(View view) {

        setInputLine("+");
    }

    public void point(View view) {

        setInputLine(".");
    }

    public void zero(View view) {

        setInputLine("0");
    }

    public void allCancel(View view) {

        enterTextView.setText("");
        inputLine = "";
        resultsTextView.setText("");
    }

    public void cancel(View view) {

        String enterText = enterTextView.getText().toString();
        int length = enterTextView.getText().length();

        if (length>0){
            enterTextView.setText(enterText.substring(0,enterText.length() - 1));
            inputLine = enterText.substring(0,enterText.length() - 1);
        }
    }

    public void otherCalculateFunctions(View view) {
    }

    public void intentMainMenu() {

        intent = new Intent(this, MainMenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void sharedPreferencesCalculateResult(String sharedKey, String result){

        editor = sPref.edit();
        editor.putString(sharedKey, result);
        editor.apply();
    }

    @Override
    public void onBackPressed(){

        String resultText;
        resultText = resultsTextView.getText().toString();

        String enterText;
        enterText = enterTextView.getText().toString();

        sharedPreferencesCalculateResult(PREFERENCES_LAST_RESULT_ON_CALCULATOR, resultText);
        sharedPreferencesCalculateResult(PREFERENCES_LAST_ENTER_ON_CALCULATOR, enterText);

        intentMainMenu();
    }
}