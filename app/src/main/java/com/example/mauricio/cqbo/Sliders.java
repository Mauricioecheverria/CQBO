package com.example.mauricio.cqbo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Mauricio on 12-05-2016.
 */
public class Sliders extends AppIntro {



    @Override
    public  void init(Bundle savedInstanceState) {


        addSlide(AppIntroFragment.newInstance("CQBO", "Este es una wea de prueba\nSi, esto es generado automaticamente",
                R.drawable.ic_slide1, Color.parseColor("#FFFFFF")));

        addSlide(AppIntroFragment.newInstance("Y otro slider mas..", Html.fromHtml("<b>Se puede en negritas</b><br><i>Tambien en cursiva jojo</i>"),
                R.drawable.ic_slide2, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("esta wea de mas funciona o no?", Html.fromHtml("<b>Asumo que aca va caleta de info</b><br><i>o no...?</i>"),
                R.drawable.ic_slide3, Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance("Falta que alguien mas vea esto por dios", Html.fromHtml("<b>Tengo mucho tiempo</b><br><i>o dedicacion</i>"),
                R.drawable.ic_slide4, Color.parseColor("#2196F3")));
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(false);
        setDoneText("Listo");
        setVibrate(true);
        setVibrateIntensity(30);


    }



    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onSlideChanged() {

    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




    public void getStarted(View v){
        loadMainActivity();
    }
}
