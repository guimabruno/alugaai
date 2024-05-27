package com.example.alugai;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Extras extends AppCompatActivity {

    Button btMeusCarros, btHistContratos, btMensagens, btCarteira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_extras);

        btMeusCarros = (Button) findViewById(R.id.btMeusCarros);
        btHistContratos = (Button) findViewById(R.id.btHistContratos);
        btMensagens = (Button) findViewById(R.id.btMensagens);
        btCarteira = (Button) findViewById(R.id.btCarteira);

        };
    }