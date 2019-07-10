package com.vttt.vuthanhtutrang.app_karaoke_cua_tui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import SQLDatabase.SQLDatabaseSource;
import model.music;

public class MusicUpDateActivity extends AppCompatActivity {

    EditText edtmaso, edttenbaihat, edtloibaihat, edtcasi;
    List<music> dsBaiHatGoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_up_date);
        addControls();
    }

    private void addControls() {
        edtmaso = findViewById(R.id.edtcasi);
        edttenbaihat = findViewById(R.id.edttenbaihat);
        edtloibaihat = findViewById(R.id.edtloibaihat);
        edtcasi = findViewById(R.id.edtcasi);
    }



}
