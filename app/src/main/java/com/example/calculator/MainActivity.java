package com.example.calculator; //Comment goes here

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.textView); //grab the view in the xml to be assigned to the "display" variable
        display.setShowSoftInputOnFocus(false); //default keyboard on device no longer pops up

        //OnClickListener
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                } //end if -- checks to see if "Enter your value" string is being displayed, if so then clear
            }
        }); //method will only look for the "Enter your value string"
    }

    private void updateText(String strToAdd) { //variable that will be added to display
        String oldStr = display.getText().toString(); //grabs text to store in this var
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    } //updates string according to cursor position

    //methods to display values
    public void zeroBTN(View view) {
        updateText("0");
    }
    public void oneBTN(View view) { updateText("1"); }
    public void twoBTN(View view) { updateText("2"); }
    public void threeBTN(View view) { updateText("3"); }
    public void fourBTN(View view) { updateText("4"); }
    public void fiveBTN(View view) { updateText("5"); }
    public void sixBTN(View view) { updateText("6"); }
    public void sevenBTN(View view) { updateText("7"); }
    public void eightBTN(View view) { updateText("8"); }
    public void nineBTN(View view) { updateText("9");}
    public void clearBTN(View view) { display.setText("");}
    public void exponentBTN(View view) { updateText("^"); }
    public void divideBTN(View view) { updateText("/"); }
    public void multiplyBTN(View view) { updateText("*"); }
    public void addBTN(View view) { updateText("+"); }
    public void subtractBTN(View view) { updateText("-"); }
    public void posNegBTN(View view) { updateText("-"); }
    public void decimalBTN(View view) { updateText("."); }
    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart(); //grabs current cursor pos
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
    public void parenthesesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) { //for loop to move cursor position
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closePar += 1;
            }
        }

        //logic to make sure every opening parentheses has a correct pairing
        if (openPar == closePar || display.getText().toString().substring(textLen - 1, textLen).equals("(")) {
            updateText("(");
        } else if (closePar < openPar && !display.getText().toString().substring(textLen - 1, textLen).equals(")")) {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void equalsBTN(View view) {}



}