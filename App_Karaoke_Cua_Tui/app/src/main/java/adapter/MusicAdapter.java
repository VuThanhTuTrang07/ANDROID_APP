package adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.vttt.vuthanhtutrang.app_karaoke_cua_tui.MainActivity;
import com.vttt.vuthanhtutrang.app_karaoke_cua_tui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.music;

public class MusicAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    private List<music> musiclist;
    private ArrayList<music> arraylist;

    public static final String DATANAME = "Database_Karaoke.sqlite";
    public static SQLiteDatabase db;
    public static String TABLE = "KaraokeSongList";

    public MusicAdapter(Context context, int item, List<music> musiclist) {
        mContext = context;
        this.musiclist = musiclist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<music>();
        this.arraylist.addAll(musiclist);
    }

    public class ViewHolder {
        TextView maso;
        TextView tenbai;
        TextView loibaihat;
        TextView tencasy;
        ImageButton imgLike;
        ImageButton imgDisLike;
    }
    public int LayViTriBatDau(String chuoi,String chuoitimkiem) {
        int vitri = 0;
        vitri = chuoi.indexOf(chuoitimkiem);//tra ve vi tri bat dau cua chuoi do
        return vitri;
    }
    @Override
    public int getCount() {
        return musiclist.size();
    }

    @Override
    public music getItem(int position) {
        return musiclist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item, null);
            holder.maso = (TextView) view.findViewById(R.id.maso);
            holder.tenbai = (TextView) view.findViewById(R.id.tenbai);
            holder.loibaihat = (TextView) view.findViewById(R.id.loibaihat);
            holder.tencasy = (TextView) view.findViewById(R.id.tencasy);
            holder.imgLike = (ImageButton) view.findViewById(R.id.imgLike);
            holder.imgDisLike = (ImageButton) view.findViewById(R.id.imgDisLike);



            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final music item = musiclist.get(position);

        holder.maso.setText(musiclist.get(position).getMa());
        holder.tencasy.setText(musiclist.get(position).getCasi());
        holder.loibaihat.setText(musiclist.get(position).getLoibh());

        if(MainActivity.chuoiTimKiem != null) {
            SpannableString textSpan = new SpannableString(item.getTen());

            int vitribatdau = LayViTriBatDau(item.getTen(), MainActivity.chuoiTimKiem);
            int vitriketthuc = MainActivity.chuoiTimKiem.length();
            textSpan.setSpan(new BackgroundColorSpan(Color.RED), vitribatdau, vitribatdau + vitriketthuc, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tenbai.setText(textSpan);

        }else  {
            holder.tenbai.setText(musiclist.get(position).getTen());
        }




        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgLike.setVisibility(View.INVISIBLE);
                holder.imgDisLike.setVisibility(View.VISIBLE);
//                xulyLike(item);
            }
        });

        holder.imgDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgDisLike.setVisibility(View.INVISIBLE);
                holder.imgLike.setVisibility(View.VISIBLE);
//                xulyDisLike(item);
            }
        });

        return view;
    }

    private void xulyDisLike(music item) {
        ContentValues values = new ContentValues();
        values.put("YEUTHICH", 0);
        int kq = MainActivity.db.update(MainActivity.TABLE,values, "MABH = ?", new String[] {item.getMa()});
        if(kq>0)
            Toast.makeText(mContext,"Đã xóa bài hát khỏi danh sách yêu thích thành công",Toast.LENGTH_SHORT).show();
    }

    private void xulyLike(music item) {
        ContentValues values = new ContentValues();
        values.put("YEUTHICH", 1);
        int kq = MainActivity.db.update(MainActivity.TABLE,values, "MABH = ?", new String[] {item.getMa()});
        if(kq>0)
            Toast.makeText(mContext,"Đã thêm bài hát vào danh sách yêu thích thành công",Toast.LENGTH_SHORT).show();
    }


    // Filter Class
    public void filter(String charText) {
        charText = MainActivity.removeAccent(charText);
        charText = charText.toLowerCase(Locale.getDefault());
        musiclist.clear();
        if (charText.length() == 0) {
            musiclist.addAll(arraylist);
        }
        else
        {
            for (music wp : arraylist)
            {

                if (wp.getTen().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    musiclist.add(wp);

                }
            }
        }
        notifyDataSetChanged();
    }



}
