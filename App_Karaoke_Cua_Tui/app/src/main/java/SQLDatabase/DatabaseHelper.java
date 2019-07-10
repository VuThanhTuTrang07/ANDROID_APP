package SQLDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATANAME = "Database_Karaoke.sqlite";
    public static final String TABLE = "KaraokeSongList";
    public static final String ID = "MABH";
    public static final String TENBAI = "TENBH";
    public static final String LOIBH = "LOIBH";
    public static final String TACGIA = "TACGIA";
    public static final String YEUTHICH = "YEUTHICH";
    Context context;
    public String duongdanDatabase = "/data//data/com.vttt.vuthanhtutrang.app_karaoke_cua_tui/databases/Database_karaoke.sqlite";// đường dẫn nơi chứa database
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATANAME, null, 1);
        this.context= context;
        duongdanDatabase = String.format(duongdanDatabase,context.getPackageName());
    }




    public void OpenDatabase(){
        db =  SQLiteDatabase.openDatabase(duongdanDatabase+DATANAME,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public boolean CreateDatabase(){
        boolean result = true;
        if(!kiemtradb()){
            this.getReadableDatabase();
            try {
                CopyDatabase();
                result = false;

            }catch (Exception e){
                throw new Error("MÁY CHƯA CÓ DATABASE");
            }
        }
        return result;

    }
    public void CopyDatabase(){
        try {
            InputStream is = context.getAssets().open(DATANAME);// đọc luồng dữ liệu  thông qua inputstream, gọi getAssets để lấy Ariang.sqlite
            OutputStream os = new FileOutputStream(duongdanDatabase+DATANAME);// nơi lưu file,ghi dữ liệu ra thông qua oupustream, nó nhận vào đường dẫn là dườngdandatabase

            byte []buffer = new byte[1024];
            int lenght = 0;
            while ((lenght = is.read(buffer)) > 0){
                os.write(buffer,0,lenght);
            }
            os.flush(); //gọi flush để chạy dữ liệu qua
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean kiemtradb(){
        try {
            String myPath = duongdanDatabase + DATANAME;
            File db = new File(myPath);
            if (db.exists()){
                return true;
            }else return false;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
