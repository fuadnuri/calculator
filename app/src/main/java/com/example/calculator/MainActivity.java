package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private Button btnAC,btnDel,btnDiv,btnMulti,btnPlus,btnMinus,btn1,btn2,btn3
            ,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnEquals,btnDot;

    private TextView textViewResult, textViewHistory;

    private String number=null;
    double first_number, last_number;

    String status = null;

    boolean operator = false;
    DecimalFormat my_format=new DecimalFormat("######.######");

    String history, current_history;

    boolean dot = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDiv = findViewById(R.id.btnDivide);
        btnMulti = findViewById(R.id.btnMulti);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnEquals = findViewById(R.id.btnEquals);
        btnDot = findViewById(R.id.btnDot);


        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    numberClick("9");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dot){

                    if (number==null){
                        number="0.";
                    }
                    else{
                        number = number+".";
                    }
                }
                textViewResult.setText(number);
                dot=false;
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    history = textViewHistory.getText().toString();
                    current_history = textViewResult.getText().toString();
                    textViewHistory.setText(history+current_history+"+");
                    if(operator){
                        if(status=="multiplication"){
                            multiply();
                        }
                        else if(status=="division"){
                            divide();
                        }
                        else if(status=="subtraction"){
                            minus();
                        }
                        else{
                            plus();
                        }
                    }
                    status="sum";
                    operator=false;
                    number=null;
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                current_history = textViewResult.getText().toString();
                textViewHistory.setText(history+current_history+"-");
                    if(operator){
                        if (status=="multiplication"){
                            multiply();
                        }
                        else if(status=="division"){
                            divide();
                        }
                        else if(status=="sum"){
                            plus();
                        }
                        else{
                            minus();
                        }
                        status="subtraction";
                        operator=false;
                        number=null;
                    }
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                current_history = textViewResult.getText().toString();
                textViewHistory.setText(history+current_history+"*");
                    if(operator){
                        if(status=="division"){
                            divide();

                        }
                        else if(status=="sum"){
                            plus();
                        }
                        else if (status=="subtraction"){
                            minus();
                        }
                        else{
                            multiply();
                        }

                    }
                    operator=false;
                    number=null;
                    status="multiplication";
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                current_history = textViewResult.getText().toString();
                textViewHistory.setText(history+current_history+"/");
                  if(operator){
                      if(status=="multiplication"){
                          multiply();
                      }
                      else if (status=="sum"){
                          plus();
                      }
                      else if(status=="subtraction"){
                          minus();
                      }
                      else{
                          divide();
                      }
                  }
                  operator=false;
                  status="division";
                  number=null;
            }
        });
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operator){
                    if(status=="sum"){
                        plus();
                    }
                    else if(status =="subtraction"){
                        minus();
                    }
                    else if(status =="multiplication"){
                        multiply();
                    }
                    else if(status =="division"){
                        divide();
                    }
                    else{
                        first_number = Double.parseDouble(textViewResult.getText().toString());
                    }
                    operator=false;
                }
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                number=null;
                status=null;

                textViewResult.setText("0");
                textViewHistory.setText("");

                first_number=0;
                last_number=0;
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(number!=null){
                    number = number.substring(0,number.length()-1);
                    textViewResult.setText(number);
                    if(number.length()<1){
                        number=null;
                    }
                }

            }
        });


    }
    public void numberClick(String view){
        if(this.number==null){
            this.number = view;
        }else{
            this.number=number+view;
        }
        textViewResult.setText(this.number);
        operator=true;
    }

    public void plus(){
        last_number = Double.parseDouble(textViewResult.getText().toString());
        first_number = first_number+last_number;

        textViewResult.setText(my_format.format(first_number));
        dot=true;
    }

    public void minus(){
        if(first_number==0){
            first_number= Double.parseDouble(textViewResult.getText().toString());

        }else{
            last_number = Double.parseDouble(textViewResult.getText().toString());
            first_number = first_number- last_number;
        }
        textViewResult.setText(my_format.format(first_number));
        dot=true;
    }

    public void multiply(){
        if(first_number==0){
            first_number= 1;
        }
        last_number=Double.parseDouble(textViewResult.getText().toString());
        first_number=first_number * last_number;
        textViewResult.setText(my_format.format(first_number));
        dot=true;
    }

    public void divide(){
        if(first_number==0){
            last_number = Double.parseDouble(textViewResult.getText().toString());
            first_number = last_number/1;
        }else{
            last_number= Double.parseDouble(textViewResult.getText().toString());
            first_number = first_number/last_number;
        }
        textViewResult.setText(my_format.format(first_number));
        dot=true;
    }
}