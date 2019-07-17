package com.jupiter.androidstudy.calculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jupiter.androidstudy.R;

import java.math.BigDecimal;

public class CalculateActivity extends AppCompatActivity {

    private TextView tvResult;
    private TextView tvReset;
    private String firstValue = "";
    private String secondValue = "";
    private boolean isFirstValue = true;
    private boolean hasDotInFirstValue = false;
    private boolean hasDotInSecondValue = false;
    private BigDecimal result = BigDecimal.valueOf(0);
    private String lastOperate = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        tvResult = findViewById(R.id.tv_result);
        tvReset = findViewById(R.id.tv_reset);
    }


    private void resetAndDisplay(){
        hasDotInFirstValue = false;
        hasDotInSecondValue = false;
        isFirstValue = true;
        firstValue = "";
        secondValue = "";
        lastOperate = "";
        result = BigDecimal.valueOf(0);
        tvResult.setText("0");
    }

    private void adjustDisplaySize(int length){
        if(length <= 24){
            tvResult.setTextSize(50);
        }else if(length <= 84){
            tvResult.setTextSize(30);
        }else{
            tvResult.setTextSize(20);
        }
    }
    private void displayResult(){
        String resultValue = result.toEngineeringString();
        adjustDisplaySize(resultValue.length());
        tvResult.setText(resultValue);
    }

    private void inCreaseAndDisplayFirstValue(String value){
        if(!firstValue.equals("0")){
            firstValue = firstValue + value;
        }
        else{
            if(value.equals("."))
                firstValue = "0.";
            else
                firstValue = value;
        }
        display();
    }

    private void inCreaseAndDisplaySecondValue(String value){
        if(!secondValue.equals("0")){
            secondValue = secondValue + value;
        }
        else{
            if(value.equals("."))
                secondValue = "0.";
            else
                secondValue = value;
        }
        display();

    }

    private void display(){
        if(isFirstValue){
            adjustDisplaySize(firstValue.length());
            tvResult.setText(firstValue);
        }else{
            adjustDisplaySize(secondValue.length());
            tvResult.setText(secondValue);
        }
    }

    private void inCreaseAndDisplay(String value){
        tvReset.setText("C");
        if(isFirstValue){
            inCreaseAndDisplayFirstValue(value);
        }
        else{
            inCreaseAndDisplaySecondValue(value);
        }
    }

    public void onClickedDigit0(View view) {
        inCreaseAndDisplay("0");

    }

    public void onClickedDigit1(View view) {
        inCreaseAndDisplay("1");
    }

    public void onClickedDigit2(View view) {
        inCreaseAndDisplay("2");
    }

    public void onClickedDigit3(View view) {
        inCreaseAndDisplay("3");
    }

    public void onClickedDigit4(View view) {
        inCreaseAndDisplay("4");
    }


    public void onClickedDigit5(View view) {
        tvReset.setText("C");
        inCreaseAndDisplay("5");
    }

    public void onClickedDigit6(View view) {
        inCreaseAndDisplay("6");
    }


    public void onClickedDigit7(View view) {
        inCreaseAndDisplay("7");
    }

    public void onClickedDigit8(View view) {
        inCreaseAndDisplay("8");
    }

    public void onClickedDigit9(View view) {
        inCreaseAndDisplay("9");
    }

    public void onClickedDot(View view) {
        if(hasDotInFirstValue && isFirstValue)
            return;

        if(hasDotInSecondValue && !isFirstValue)
            return;

        inCreaseAndDisplay(".");
        if(isFirstValue)
            hasDotInFirstValue = true;
        else
            hasDotInSecondValue = true;
    }

    public void onClickedReset(View view) {
        adjustDisplaySize(2);
        tvReset.setText("AC");
        resetAndDisplay();
    }


    public void onClickedAddSub(View view) {
        if(isFirstValue){
            if(firstValue.equals("0") || firstValue.equals("")){
                return;
            }

            if(firstValue.startsWith("-")){
                firstValue = firstValue.substring(1);
            }
            else if(firstValue.endsWith(".")){
                firstValue = firstValue.substring(0, firstValue.length() -1);
                hasDotInFirstValue =false;
            }
            else{
                firstValue = "-" + firstValue;
            }
        }else{
            if(secondValue.equals("0") || secondValue.equals("")){
                return;
            }
            if(secondValue.startsWith("-")){
                secondValue = secondValue.substring(1);
            }else if(secondValue.endsWith(".")){
                secondValue = secondValue.substring(0, secondValue.length() - 1);
                hasDotInSecondValue = false;
            }
            else{
                secondValue = "-" + secondValue;
            }
        }
        display();
    }

    public void onClickedPercent(View view) {
        if(firstValue.equals("0") || firstValue.equals("")){
            return;
        }

        if(firstValue.equals("0.")){
            firstValue = "0";
            display();
        }

        BigDecimal firstBigValue = new BigDecimal(firstValue);
        BigDecimal firstBigResult = firstBigValue.divide(BigDecimal.valueOf(100));
        firstValue = firstBigResult.toEngineeringString();
        display();
    }

    public void onClickedDivider(View view) {

        isFirstValue = false;
        lastOperate = "/";

        if(secondValue.equals("")){
            return;
        }

        BigDecimal secondBigValue = new BigDecimal(secondValue);

        if(secondBigValue.intValue() == 0){
            adjustDisplaySize(12);
            tvResult.setText("Not a number");
            firstValue = "";
            secondValue = "";
            hasDotInFirstValue = false;
            hasDotInSecondValue = false;
            isFirstValue = true;
            return;
        }

        BigDecimal firstBigValue = new BigDecimal(firstValue);
        result = firstBigValue.divide(secondBigValue, 23, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();

        displayResult();
        firstValue = result.toEngineeringString();
        secondValue = "";
    }

    public void onClickedMulti(View view) {

        isFirstValue = false;
        lastOperate = "*";

        if(secondValue.equals("")){
            return;
        }
        BigDecimal firstBigValue = new BigDecimal(firstValue);
        BigDecimal secondBigValue = new BigDecimal(secondValue);

        result = firstBigValue.multiply(secondBigValue);
        displayResult();
        firstValue = result.toEngineeringString();
        secondValue = "";

    }

    public void onClickedSub(View view) {

        isFirstValue = false;
        lastOperate = "-";

        if(secondValue.equals("")){
            return;
        }
        BigDecimal firstBigValue = new BigDecimal(firstValue);
        BigDecimal secondBigValue = new BigDecimal(secondValue);

        result = firstBigValue.subtract(secondBigValue);
        displayResult();
        firstValue = result.toEngineeringString();
        secondValue = "";

    }

    public void onClickedAdd(View view) {

        isFirstValue = false;
        lastOperate = "+";

        if(secondValue.equals("")){
            return;
        }
        BigDecimal firstBigValue = new BigDecimal(firstValue);
        BigDecimal secondBigValue = new BigDecimal(secondValue);

        result = firstBigValue.add(secondBigValue);
        displayResult();
        firstValue = result.toEngineeringString();
        secondValue = "";

    }

    public void onClickedEqual(View view) {

        if(firstValue.equals("")){
            return;
        }

        isFirstValue = false;
        if(secondValue.equals("")){
            result = new BigDecimal(firstValue);
            firstValue = "";
            isFirstValue = true;
            displayResult();
            return;
        }

        BigDecimal firstBigValue = new BigDecimal(firstValue);
        BigDecimal secondBigValue = new BigDecimal(secondValue);
        if(lastOperate.equals("+")){
            result = firstBigValue.add(secondBigValue);
        }
        else if(lastOperate.equals("-")){
            result = firstBigValue.subtract(secondBigValue);
        }
        else if(lastOperate.equals("*")){
            result = firstBigValue.multiply(secondBigValue);
        }
        else if(lastOperate.equals("/")){
            result = firstBigValue.divide(secondBigValue);
        }
        displayResult();
        firstValue = result.toEngineeringString();

    }



}
