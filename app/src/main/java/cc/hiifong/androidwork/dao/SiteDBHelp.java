package cc.hiifong.androidwork.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import cc.hiifong.androidwork.model.Site;

public class SiteDBHelp extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sites";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "sitesInfo";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    " id TEXT NOT NULL, " +
                    " name TEXT NOT NULL, " +
                    " phoneNo TEXT, " +
                    " address TEXT, PRIMARY KEY (id));";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_PHONE_NO = "phoneNo";
    private static final String COL_ADDRESS = "address";
    public SiteDBHelp(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertBD(Site site){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, site.getId());
        values.put(COL_NAME, site.getName());
        values.put(COL_PHONE_NO, site.getPhoneNo());
        values.put(COL_ADDRESS, site.getAddress());
        long rowId = database.insert(TABLE_NAME, null, values);
        database.close();
        return rowId;
    }

    public int updateDB(Site site){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, site.getName());
        values.put(COL_PHONE_NO, site.getPhoneNo());
        values.put(COL_ADDRESS, site.getAddress());
        String whereClause = COL_ID + "='" + site.getId() + "'";
        int count = database.update(TABLE_NAME, values, whereClause, null);
        database.close();
        return count;
    }

    public int delete(String id){
        SQLiteDatabase database =  getWritableDatabase();
        String whereClause = COL_ID + "='" + id + "'";
        int count = database.delete(TABLE_NAME, whereClause, null);
        database.close();
        return count;
    }

    public void fillDB(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "select * from sqlite_master where name = " + "'" + TABLE_NAME + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.getCount() == 0){
            database.execSQL(TABLE_CREATE);
            ContentValues[] values = new ContentValues[4];
            for (int i = 0; i < values.length; i++){
                values[i] = new ContentValues();
            }
            values[0].put("id", "wdc");
            values[0].put("name", "王大锤");
            values[0].put("phoneNo", "888888888");
            values[0].put("address", "银滩大道88号");

            values[1].put("id", "hiifong");
            values[1].put("name", "hiifong");
            values[1].put("phoneNo", "888888888");
            values[1].put("address", "南珠大道9号");

            values[2].put("id", "ww");
            values[2].put("name", "王五");
            values[2].put("phoneNo", "666666");
            values[2].put("address", "银滩大道66号");

            values[3].put("id", "qq");
            values[3].put("name", "腾讯");
            values[3].put("phoneNo", "00000000");
            values[3].put("address", "地下城");

            for (ContentValues row: values){
                database.insert(TABLE_NAME, null, row);
            }
            database.close();
        }
    }
    public ArrayList<String> getInfo(String name){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT name,phoneNo,address FROM " + TABLE_NAME + " WHERE name LIKE ?";
        String[] args = { "%" + name + "%"};
        Cursor cursor = database.rawQuery(sql, args);
        ArrayList<String> info = new ArrayList<>();
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()){
            String str = "";
            for (int i = 0; i < columnCount; i++){
                str += cursor.getString(i) + "\n ";
                info.add(str);
            }
        }
        cursor.close();
        database.close();
        return info;
    }

    public ArrayList<Site> getAllSites(){
        SQLiteDatabase database = getWritableDatabase();
        String[] columns = { COL_ID, COL_NAME, COL_PHONE_NO, COL_ADDRESS};
        Cursor cursor = database.query(TABLE_NAME, columns, null,null, null, null, null);
        ArrayList<Site> sites = new ArrayList<Site>();
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phoneNo = cursor.getString(2);
            String address = cursor.getString(3);
            Site site = new Site(id, name, phoneNo, address);
            sites.add(site);
        }
        cursor.close();
        database.close();
        return sites;
    }
}
