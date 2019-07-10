package com.vttt.vuthanhtutrang.app_karaoke_cua_tui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import SQLDatabase.SQLDatabaseSource;
import model.music;

public class MusicAddActivity extends AppCompatActivity {

    EditText edtmaso, edttenbaihat, edtloibaihat, edtcasi;
    List<music> musiclist;
    ArrayList<music> arraylist;

    public static final String DATANAME = "Database_Karaoke.sqlite";
    public static SQLiteDatabase db;
    public static String TABLE = "KaraokeSongList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_add);
        addControls();
    }

    private void addControls() {
        edtmaso = findViewById(R.id.edtcasi);
        edttenbaihat = findViewById(R.id.edttenbaihat);
        edtloibaihat = findViewById(R.id.edtloibaihat);
        edtcasi = findViewById(R.id.edtcasi);
    }

    public void xulyThem(View view) {

        music item = new music();

        if (edtmaso.getText().length()==0) {
            Toast.makeText(MusicAddActivity.this, "Vui Lòng Nhập Mã Số!!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (edttenbaihat.getText().length()==0) {
            Toast.makeText(MusicAddActivity.this, "Vui Lòng Nhập Tên Bài Hát!!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (edtloibaihat.getText().length()==0) {
            Toast.makeText(MusicAddActivity.this, "Vui Lòng Nhập Lời Bài Hát!!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (edtcasi.getText().length()==0) {
            Toast.makeText(MusicAddActivity.this, "Vui Lòng Nhập Tên Ca Sĩ!!!", Toast.LENGTH_LONG).show();
            return;
        }

        item.setMa(edtmaso.getText().toString());
        item.setTen(edttenbaihat.getText().toString());
        item.setLoibh(edtloibaihat.getText().toString());
        item.setCasi(edtcasi.getText().toString());
        item.setThich(0);

        ContentValues values = new ContentValues();
        values.put("MABH", item.getMa());
        values.put("TENBH", item.getTen());
        values.put("LOIBH", item.getLoibh());
        values.put("TACGIA", item.getCasi());
        values.put("YEUTHICH", item.getThich());

        long kq = db.insert(TABLE, null, values);
        if (kq > 0) {
            Toast.makeText(MusicAddActivity.this, "Lưu Thành Công", Toast.LENGTH_LONG).show();
            // Hàm hiển thị listview vừa nhập ở màng hình khác
            Intent intent= new Intent(MusicAddActivity.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MusicAddActivity.this, "Lưu Thất Bại", Toast.LENGTH_LONG).show();
        }

    }


}
