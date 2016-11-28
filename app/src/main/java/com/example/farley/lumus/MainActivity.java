package com.example.farley.lumus;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farley.lumus.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region Vars
    private static final int RECORD_CODE = 12;
    private boolean conjuroIdenty = false;
    Camera cam;
    //endegion

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.btnGrabar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        initRecord();
    }

    private void initRecord(){
        conjuroIdenty = false;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Conjuro...");
        startActivityForResult(intent,RECORD_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==RECORD_CODE && resultCode==RESULT_OK){
            ArrayList<String> palabras = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            aplyConjuro(palabras);
        }
    }

    private void aplyConjuro(ArrayList<String> palabras){
        for (String pal:palabras){
            Log.i("haur","Palabras: "+pal);
            if (!conjuroIdenty)
                indentifyConjuro(pal);
        }
    }

    private void indentifyConjuro(String palabra){
        switch (palabra){
            case "Loomis":
                conjuroIdenty = true;
                onOffLuz();
                break;
            case "neumos":
                conjuroIdenty = true;
                onOffLuz();
                break;
            case "lumos":
                conjuroIdenty = true;
                onOffLuz();
                break;

        }
    }

    private void onOffLuz(){
        cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        cam.setParameters(p);
        cam.startPreview();
    }

    @Override
    protected void onPause() {
        cam.stopPreview();
        cam.release();
        super.onPause();
    }
}
