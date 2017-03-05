package com.example.producer.bmiprogram;


import android.os.Bundle;
import android.app.Activity;
import android.text.*;
import android.view.View;
import android.widget.*;


public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText h, w;
        final RadioGroup sex;
        Button calc;
        final TextView viewerBmi;

        h = (EditText) findViewById(R.id.heights_message);
        w = (EditText) findViewById(R.id.weights_message);
        viewerBmi = (TextView) findViewById(R.id.Viewer);
        calc =(Button) findViewById(R.id.calcButton);
        sex = (RadioGroup) findViewById(R.id.choiceSex);


        calc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String h1 = h.getText().toString();
                String w1 = w.getText().toString();
                String m = "男性";
                String f = "女性";
                //getCheckedRadioButtonId의 리턴값은 선택된 라디오 버튼의 id 값.
                int id = sex.getCheckedRadioButtonId();
                //cs는 초이스 섹스.
                RadioButton cs = (RadioButton) findViewById(id);


                if (TextUtils.isEmpty(h1)) {
                    h.setError("身長を入力してください！");
                    h.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(w1)) {
                    w.setError("体重を入力してください！");
                    w.requestFocus();
                    return;
                }

                double height = Double.parseDouble(h1);
                double weight = Double.parseDouble(w1);
                double BMI = calcBmi(height, weight);

                String bmiResult = String.format("%.2f ", BMI);
                String bmiDiagnosis;
                bmiDiagnosis = diagnosisForMen(BMI);
                viewerBmi.setText(String.valueOf(bmiResult)+"\n"+bmiDiagnosis);

                if (m.equals(cs.getText().toString())){
                    bmiDiagnosis = diagnosisForMen(BMI);
                    viewerBmi.setText(String.valueOf(bmiResult)+"\n"+bmiDiagnosis);

                }else if (f.equals(cs.getText().toString())){
                    bmiDiagnosis = diagnosisForWomen(BMI);
                    viewerBmi.setText(String.valueOf(bmiResult)+"\n"+bmiDiagnosis);
                }

            }
        });
    }

    private double calcBmi (double height, double weight) {
        return weight/((height/100)*(height/100));
    }

    private String diagnosisForMen (double bmiResult){
        if(bmiResult<16){
            return "やせすぎです。\n" +
                    "体重を増やしてください！";
        }else if(bmiResult<18.5){
            return "やせています。";
        }else if (bmiResult<25){
            return "丁度良いです！ \n"+
                    "このまま管理してください！";
        }else if (bmiResult<30){
            return "少し、太っているかも…";
        } else if(bmiResult<33){
            return "注意！肥満です！";
        }else {
            return "...大丈夫ですか？死んじゃいますよ？";
        }
    }

    private String diagnosisForWomen (double bmiResult){
        if(bmiResult<16){
            return "やせすぎです。\n" +
                    "体重を増やしてください！";
        }else if(bmiResult<18.5){
            return "やせています。";
        }else if (bmiResult<23){
            return "丁度良いです！ \n"+
                    "このまま管理してください！";
        }else if (bmiResult<27){
            return "少し、太っているかも…";
        } else if (bmiResult<30){
            return "注意！肥満です！";
        }else{
            return "...大丈夫ですか？死んじゃいますよ？";
        }
    }

    /** Called when the user clicks the Send button */
}
