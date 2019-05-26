package com.example.tgsmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText ediEmail;
    EditText ediNim;
    EditText ediNama;
    EditText ediKelas;
    Button btnEmail;
    Email emaill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emaill = new Email(this);

        ediNim = (EditText)findViewById(R.id.edinim);
        ediNama = (EditText)findViewById(R.id.edinama);
        ediKelas = (EditText)findViewById(R.id.edikelas);
        ediEmail = (EditText)findViewById(R.id.ediemail);
        btnEmail = (Button)findViewById(R.id.btnEmail);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = ediNim.getText().toString();
                String nama = ediNama.getText().toString();
                String kelas = ediKelas.getText().toString();
                String email = ediEmail.getText().toString();
                emaill.execute(nim,nama,kelas,email);
            }
        });
    }
}
