package com.demo.orders.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.demo.orders.model.CustomersDataSource;
import com.demo.orders.util.BitmapUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class JSONResult {

    public Context context;
    public DBHelper dbHelper;
    BitmapUtility bitmapUtility;

    public JSONResult(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
        this.bitmapUtility = new BitmapUtility(context);
    }

    //get the SQLite Database List to JSONArray
    public JSONArray getJSONArray(String tableName) {
        //get the Table Name
        Cursor cursor = dbHelper.table(tableName);
        JSONArray resultSet = new JSONArray();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                Log.e("TAG_NAME", cursor.getString(i));
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            Log.e("TAG_NAME", e.getMessage());
                        }
                    }
                }
                resultSet.put(rowObject);
                cursor.moveToNext();
            }
        }
        cursor.close();
        Log.d("TAG_NAME", resultSet.toString());
        return resultSet;
    }

    //get the SQLite Database List to JSONObject
    public JSONObject getJSONObject(Cursor cursor) {
        //get the Table Name
//        Cursor cursor = dbHelper.table(tableName);
        JSONObject rowObject = new JSONObject();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int totalColumn = cursor.getColumnCount();
                rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
                            if (cursor.getString(i) != null) {
                                Log.e("TAG_NAME", cursor.getString(i));
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            Log.e("TAG_NAME", e.getMessage());
                        }
                    }
                }

                cursor.moveToNext();
            }
        }
        cursor.close();

//        assert rowObject != null;
        Log.d("JSON OBJECT RESULT", rowObject.toString());
        return rowObject;
    }

    //get the Customer List to JSONArray
    public JSONArray getCustomersArray(List<CustomersDataSource> tableList) {
        //get the Table Name
        JSONArray jsonArray = new JSONArray();
        JSONObject rowObject = new JSONObject();
        for (CustomersDataSource dataSource : tableList) {
            try {
                rowObject.put("customerID", dataSource.getCustomerID());
                rowObject.put("customerName", dataSource.getCustomerName());
                rowObject.put("date", dataSource.getCurrentDate());
                //Insert the JSON OBJECT TO JSONArray
                jsonArray.put(rowObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("JSON Array RESULT", jsonArray.toString());
        return jsonArray;
    }

    //get the List to JSONArray
    public JSONObject getSalesJSONObject(List<SalesDataSource> tableList) {
        //get the Table Name
        JSONObject rowObject = null;
        for (SalesDataSource dataSource : tableList) {

        }

        assert rowObject != null;
        Log.d("JSON OBJECT RESULT", rowObject.toString());
        return rowObject;
    }

    //get the SQLite Database List to JSONArray using cursor
    public JSONArray getJSONArrayCursor(Cursor cursor) {
        //get the Table Name
        JSONArray resultSet = new JSONArray();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
//                            Log.e("TAG_TYPE", "getJSONArrayCursor: getType : "+cursor.getColumnName(i) +" = "+cursor.getString(i));
//                            if (cursor.getType(i) == Cursor.FIELD_TYPE_STRING ||
//                                    cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER || cursor.getType(i) == Cursor.FIELD_TYPE_NULL||
//                                    cursor.getType(i) == Cursor.FIELD_TYPE_FLOAT) {

                            if (cursor.getString(i) != null) {
                                Log.e("TAG_NAME", cursor.getColumnName(i) + " = " + cursor.getString(i));
                                rowObject.put(cursor.getColumnName(i), cursor.getString(i));
//                                }
//
//                            }else if (cursor.getType(i) == Cursor.FIELD_TYPE_BLOB) {
//                                String image=bitmapUtility.getBaseImage(cursor.getBlob(i));
//                                Log.e("TAG_Image", String.valueOf(cursor.getBlob(i)));
//                                rowObject.put(cursor.getColumnName(i), image);
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            Log.e("TAG_NAME", e.getMessage());
                        }
                    }
                }
                resultSet.put(rowObject);
                cursor.moveToNext();
            }
        }
        System.out.println("JSONArray" + resultSet.toString());
        cursor.close();
        return resultSet;
    }


    //inventory jsonArray

    //get the SQLite Database List to JSONArray using cursor
    public JSONArray getJSONArrayIventoryCursor(Cursor cursor) {
        //get the Table Name
        JSONArray resultSet = new JSONArray();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();
                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        try {
//                            Log.e("TAG_TYPE", "getJSONArrayCursor: getType : "+cursor.getColumnName(i) +" = "+cursor.getString(i));
                            if (cursor.getType(i) == Cursor.FIELD_TYPE_STRING ||
                                    cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER || cursor.getType(i) == Cursor.FIELD_TYPE_NULL ||
                                    cursor.getType(i) == Cursor.FIELD_TYPE_FLOAT) {

                                if (cursor.getString(i) != null) {
                                    Log.e("TAG_NAME", cursor.getColumnName(i) + " = " + cursor.getString(i));
                                    rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                                }
//
                            } else if (cursor.getType(i) == Cursor.FIELD_TYPE_BLOB) {
                                String image = bitmapUtility.getBaseImage(cursor.getBlob(i));
                                Log.e("TAG_Image", String.valueOf(cursor.getBlob(i)));
                                rowObject.put(cursor.getColumnName(i), image);
                            } else {
                                rowObject.put(cursor.getColumnName(i), "");
                            }
                        } catch (Exception e) {
                            Log.e("TAG_NAME", e.getMessage());
                        }
                    }
                }
                resultSet.put(rowObject);
                cursor.moveToNext();
            }
        }
        System.out.println("JSONArray" + resultSet.toString());
        cursor.close();
        return resultSet;
    }

    public JSONArray getJSONArrayCursor1(Cursor crs) {
        JSONArray arr = new JSONArray();
        crs.moveToFirst();
        while (!crs.isAfterLast()) {
            int nColumns = crs.getColumnCount();
            JSONObject row = new JSONObject();
            for (int i = 0; i < nColumns; i++) {
                String colName = crs.getColumnName(i);
                if (colName != null) {
                    String val = "";
                    try {
                        switch (crs.getType(i)) {
                            case Cursor.FIELD_TYPE_BLOB:
                                row.put(colName, crs.getBlob(i).toString());
                                break;
                            case Cursor.FIELD_TYPE_FLOAT:
                                row.put(colName, crs.getDouble(i));
                                break;
                            case Cursor.FIELD_TYPE_INTEGER:
                                row.put(colName, crs.getLong(i));
                                break;
                            case Cursor.FIELD_TYPE_NULL:
                                row.put(colName, null);
                                break;
                            case Cursor.FIELD_TYPE_STRING:
                                row.put(colName, crs.getString(i));
                                break;
                        }
                    } catch (JSONException e) {
                    }
                }
            }
            arr.put(row);
            if (!crs.moveToNext())
                break;
        }
        System.out.println("JSONArray " + arr.toString());
        crs.close(); // close the cursor
        return arr;
    }

}
