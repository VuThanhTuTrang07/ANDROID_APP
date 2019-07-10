package com.vttt.vuthanhtutrang.app_karaoke_cua_tui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import SQLDatabase.DatabaseHelper;
import SQLDatabase.SQLDatabaseSource;
import adapter.MusicAdapter;
import model.music;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {
    public static final String TAG ="MyMessage";
    ListView lvBaiHatGoc;
    List<music> dsBaiHatGoc;
    MusicAdapter adapterBaiHatGoc;
    TabHost tabHost;
    TabHost.TabSpec tabSpec;
    android.support.v7.widget.SearchView searchView;
    public  static final String DATANAME = "Database_Karaoke.sqlite";
    public static SQLDatabaseSource db;
    public static String TABLE = "KaraokeSongList";
    ArrayAdapter<String> adapter;
    ArrayList<music>  musicArrayList;
    public static music selectedmusic;
    public static String chuoiTimKiem = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateDb();
        addControl();
        addEvent();
    }

    private void addEvent() {
        lvBaiHatGoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedmusic = adapterBaiHatGoc.getItem(position);
            }
        });
        lvBaiHatGoc.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedmusic = adapterBaiHatGoc.getItem(position);
                return false;
            }
        });
    }

//    private void loadDuLieu() {
//        final SQLDatabaseSource sqlDatabaseSource = new SQLDatabaseSource(this);
//        musicArrayList = sqlDatabaseSource.LayDanhSachBaiHatTheoMa();
//    }

    // khởi tạo database
    public void CreateDb(){
        DatabaseHelper sql = new DatabaseHelper(this);
        try{
            sql.CreateDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addControl(){
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        tabSpec = tabHost.newTabSpec("tab1");
        tabSpec.setIndicator("DANH SÁCH BÀI HÁT");
        tabSpec.setContent(R.id.dsBaihat);
        tabHost.addTab(tabSpec);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
        tabSpec2.setIndicator("BÀI HÁT YÊU THÍCH");
        tabSpec2.setContent(R.id.dsYeuthich);
        tabHost.addTab(tabSpec2);
        lvBaiHatGoc= (ListView) findViewById(R.id.lvdanhsach);


        db = new SQLDatabaseSource(getApplicationContext());
        dsBaiHatGoc = new ArrayList<music>();
        dsBaiHatGoc = db.LayDanhSachBaiHat();
        adapterBaiHatGoc = new MusicAdapter(MainActivity.this,R.layout.item,dsBaiHatGoc);
        lvBaiHatGoc.setAdapter(adapterBaiHatGoc);
        searchviewchotabhost();

    }
    public static String removeAccent(String s) {//bat che do khong dau

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
    public void searchviewchotabhost(){
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {

                searchView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchView.setIconified(false);
                        searchView.requestFocusFromTouch();
                    }
                },100);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        MenuItem item = menu.findItem(R.id.search_view);
        searchView = (android.support.v7.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        searchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchView.requestFocusFromTouch();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String chuoi) {

        if(TextUtils.isEmpty(chuoi)){
            adapterBaiHatGoc.filter("");
            lvBaiHatGoc.clearTextFilter();
        }else{

            adapterBaiHatGoc.filter(chuoi.toString());

        }

        chuoiTimKiem = chuoi;


        return true;
    }


}
