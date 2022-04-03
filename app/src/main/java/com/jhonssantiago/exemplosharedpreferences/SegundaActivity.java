package com.jhonssantiago.exemplosharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TextView textViewNome;
    private String[] cores = {"Verde","Vermelha","Azul"};
    private ListView listViewCores;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textViewNome = findViewById(R.id.textViewNome);   //fazer a conversões de XML para o tipo de classe específica
        listViewCores = findViewById(R.id.listViewCores);
        Intent intent = getIntent();  //obter uma Intent
        String nome_usuario = intent.getStringExtra("nome_usuario");  //obter o dado vinculado ao objeto de mensagem
        textViewNome.setText(nome_usuario);  //configurar o EditText com o nome do usuário
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,cores);  //instanciar um Adapter, passando o layout e os dados
        listViewCores.setAdapter(adapter);  //definir o adapter criado ao ListView
        listViewCores.setOnItemClickListener(this);  //definir qual classe implementou o listener, neste caso, foi essa própria
    }

    private void gravar(String escolhido) {

        SharedPreferences sharedPreferences = getSharedPreferences("dados",MODE_PRIVATE);  //obter um SharedPreferences, neste caso, as preferências são acessíveis por todo app, basta ter acesso ao objeto Context. O nome do arquivo é dados e o modo privado defina que o arquivo é exclusivo do app.
        SharedPreferences.Editor editor = sharedPreferences.edit(); //obter um Editor para poder persistir os dados.
        editor.putString("nome_usuario",textViewNome.getText().toString()); //gravando o nome do usuário. Observe que trata-se de um mapeamento, nome-valor.
        editor.putString("cor_usuario",escolhido);   //gravando a cor
        boolean resposta = editor.commit();  //gravar dos dados de forma síncrona. Devolve um valor boleano
        if(resposta){  //se a resposta for true, invoque a outra tela
            Intent intent = new Intent(this,TerceiraActivity.class);
            startActivity(intent);
        }else{  //se for false, problemas na persistência
            Toast.makeText(this,"Não salvo",Toast.LENGTH_SHORT).show();
        }

    }//

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String escolhido = cores[i];   //obter a posição do item da lista selecionado e obter a cor determinada no vetor de cores
        gravar(escolhido);   //chamar o método gravar passando essa cor como parâmetro
    }
}