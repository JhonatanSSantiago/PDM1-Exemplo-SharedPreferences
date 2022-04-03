package com.jhonssantiago.exemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TerceiraActivity extends AppCompatActivity {
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceira);

        layout = findViewById(R.id.layout);   //conversão XML para tipo de classe específico
        ler();  //chamar o método ler
    }

    private void ler() {

        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);
        if(sharedPreferences.contains("nome_usuario") /*verifica se existe a chave*/ && sharedPreferences.contains("cor_usuario")){
            Toast.makeText(this, sharedPreferences.getString("nome_usuario","nenhum"),Toast.LENGTH_SHORT).show();
            String cor = sharedPreferences.getString("cor_usuario","Branca");
            setarCor(cor);//os dados nenhum e branca serão mostrados caso não encontre o valor armazenado corresponde às chaves: nome_usuario e cor_usuario. Seria um valor default.
        }else{
            Toast.makeText(this,"ERRO no arquivo",Toast.LENGTH_SHORT).show();
        }

    }//

    private void setarCor(String cor) {

        switch (cor){
            case "Verde":
                layout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                break;

            case "Azul":
                layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                break;

            case "Vermelha":
                layout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                break;

            case "Branca":
                layout.setBackgroundColor(getResources().getColor(android.R.color.white));
                break;

        }

    }//
}