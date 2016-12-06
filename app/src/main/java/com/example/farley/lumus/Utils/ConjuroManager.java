package com.example.farley.lumus.Utils;

import android.content.Context;

import com.example.farley.lumus.MainActivity;

/**
 * Created by Hamilton Urbano on 04/12/2016.
 */

public class ConjuroManager {

    Context context;
    MainActivity mainActivity;

    public ConjuroManager(Context context) {
        this.mainActivity = (MainActivity) context;
    }

    public void indentifyConjuro(String palabra){
        switch (palabra){
            case "Loomis":
                mainActivity.conjuroIdenty = true;
                mainActivity.onOffLuz();
                break;

            case "neumos":
                mainActivity.conjuroIdenty = true;
                mainActivity.onOffLuz();
                break;

            case "lumos":
                mainActivity.conjuroIdenty = true;
                mainActivity.onOffLuz();
                break;

            case "Knox":
                mainActivity.noxConjuro();
                break;

            case "nox":
                mainActivity.noxConjuro();
                break;

            case "alohomora":
                mainActivity.alohomoraConjuro();
                break;

            case "Rocio":
                mainActivity.crucioConjuro();
                break;

            case "lucio":
                mainActivity.crucioConjuro();
                break;

            case "cruzio":
                mainActivity.crucioConjuro();
                break;

            case "crucio":
                mainActivity.crucioConjuro();
                break;

            case "expecto patronum":
                mainActivity.espectoPatronum();
                break;

            case "expecto Patron":
                mainActivity.espectoPatronum();
                break;

            case "expecto Patron on":
                mainActivity.espectoPatronum();
                break;

            case "operation":
                mainActivity.apareciumConjuro();
                break;

            case "population":
                mainActivity.apareciumConjuro();
                break;

            case "Expelliarmus":
                mainActivity.expelliarmusConjuro();
                break;

            case "impero":
                break;

            case "petrificus totalus":
                break;

            case "avada kedavra":
                break;

        }
    }


}
