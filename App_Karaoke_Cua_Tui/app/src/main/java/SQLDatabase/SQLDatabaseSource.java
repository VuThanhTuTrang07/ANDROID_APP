package SQLDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

import model.music;

public class SQLDatabaseSource extends DatabaseHelper {


    public SQLDatabaseSource(Context context){
        super(context);
    }
    public ArrayList<music> LayDanhSachBaiHatTheoMa(String tenbaihat) {

        ArrayList<music> list = new ArrayList<>();
        try {
            OpenDatabase();
            String[] columns = { DatabaseHelper.ID,DatabaseHelper.TENBAI,DatabaseHelper.LOIBH,DatabaseHelper.TACGIA };
            String truyvan = "Select * From " + DatabaseHelper.TABLE + " Where " + DatabaseHelper.TENBAI + " LIKE '% "+ tenbaihat.toLowerCase() +" %'";
            Cursor c = db.rawQuery(truyvan,null);//truy  vấn dữ liệu trong database
            c.moveToFirst();// duyệt con trỏ về vị trí dầu tiên
            while ((!c.isAfterLast())){// con trỏ c không là vị trí cuối cùng thì thực hiện vòng lặp
                music item = new music();
                item.setMa(c.getString(0));
                item.setTen(c.getString(1));
                item.setCasi(c.getString(3));
                list.add(item);
                c.moveToNext();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return list;
    }

    public ArrayList<music> LayDanhSachBaiHat() {

        ArrayList<music> list = new ArrayList<>();
        try {
            OpenDatabase();
            String[] columns = {DatabaseHelper.ID,DatabaseHelper.TENBAI,DatabaseHelper.LOIBH,DatabaseHelper.TACGIA};
            Cursor c = db.query(DatabaseHelper.TABLE,columns,null,null,null,null,null);//truy  vấn dữ liệu trong database
            c.moveToFirst();// duyệt con trỏ về vị trí dầu tiên
            while ((!c.isAfterLast())){// con trỏ c không là vị trí cuối cùng thì thực hiện vòng lặp
                music item = new music();
                item.setMa(c.getString(0));
                item.setTen(c.getString(1));
                item.setLoibh(c.getString(2));
                item.setCasi(c.getString(3));
                list.add(item);
                c.moveToNext();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close();
        }

        return list;
    }

    public long insert (music item) {
        ContentValues values = new ContentValues();
        values.put("MABH", item.getMa());
        values.put("TENBH", item.getTen());
        values.put("LOIBH", item.getLoibh());
        values.put("TACGIA", item.getCasi());
        values.put("YEUTHICH", 0);
        return db.insert("music", null, values);
    }

    public int update(music item) {
        ContentValues values = new ContentValues();
        values.put("MABH", item.getMa());
        values.put("TENBH", item.getTen());
        values.put("LOIBH", item.getLoibh());
        values.put("TACGIA", item.getCasi());
        values.put("YEUTHICH", 0);
        return db.update("music", values, "MABH = ?", new String[] {item.getMa()});
    }

    public int delete(String ma) {
        return db.delete("music", "MABH = ?", new String[]{ma});
    }
}
