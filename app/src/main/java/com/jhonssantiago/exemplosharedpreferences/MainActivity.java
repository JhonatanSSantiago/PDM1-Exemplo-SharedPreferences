package com.jhonssantiago.exemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        buttonNext = findViewById(R.id.buttonNext);
    }

    public void clicar(View view){
        String nome = editTextNome.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);
        String name = sharedPreferences.getString("nome_usuario", "");
        Toast.makeText(this,"SP: "+name+" ET: "+nome,Toast.LENGTH_SHORT).show();
        if(name.equals(nome)){
            Intent intent = new Intent(this, TerceiraActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, SegundaActivity.class);
            intent.putExtra("nome_usuario",nome);
            startActivity(intent);
        }

    }//
}