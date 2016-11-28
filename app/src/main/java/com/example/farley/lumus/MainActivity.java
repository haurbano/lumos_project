package com.example.farley.lumus;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.farley.lumus.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region Vars
    private static final int RECORD_CODE = 12;
    private boolean conjuroIdenty = false;
    Camera camera;
    Camera.Parameters parameters;
    //endegion

    ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.btnGrabar.setOnClickListener(this);

        camera = Camera.open(0);

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


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onOffLuz(){
        parameters = this.camera.getParameters();
        if (parameters.getFlashMode().equals(Camera.Parameters.FLASH_MODE_TORCH))
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        else
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        this.camera.setParameters(parameters);
        this.camera.startPreview();
    }


    @Override
    protected void onDestroy() {
        if (camera!=null){
            parameters = this.camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            this.camera.stopPreview();
        }
        super.onDestroy();
    }
}
