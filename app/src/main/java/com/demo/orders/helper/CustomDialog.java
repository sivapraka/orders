package com.demo.orders.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.demo.orders.R;
import com.demo.orders.adapter.NewOrderEnqueryAdapter;
import com.demo.orders.db.DBHelper;
import com.demo.orders.db.DatabaseList;
import com.demo.orders.util.Constants;

import java.util.List;

import androidx.appcompat.app.AlertDialog;

//This class is used for show the dialogs in the application
public class CustomDialog {
    public Context context;
    DBHelper dbHelper;
    DatabaseList databaseList;
    CommonClass commonClass;
    private String TAG = CustomDialog.class.getSimpleName();

    public CustomDialog(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        databaseList = new DatabaseList(context);
        commonClass = new CommonClass(context);
    }


    /* Context is converted to Activity.*/
    public Activity convertActivity(Context context) {
        return (Activity) context;
    }

    //Display the Default Toast
    public void ToastDisplay(String message) {
        Toast.makeText(convertActivity(context), message, Toast.LENGTH_SHORT).show();
    }
    //Show the Custom Toast Display
    //Pass the activity and Display Message and Its Status
    public void CustomToast(Activity activity, String message, String update) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_toast, null);
        ImageView image = toastLayout.findViewById(R.id.imageViewCustomImage);
        TextView textView = toastLayout.findViewById(R.id.textViewMessage);
        if (update.equals(Constants.success)) {
            image.setImageResource(R.drawable.ic_checked);
        } else if (update.equals(Constants.fail)) {
            image.setImageResource(R.drawable.ic_cancel);
        }
        textView.setText(message);
        //This line make the Tricks
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }

    public void CustomToast(Activity activity, String message, int list) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_toast, null);
        ImageView image = toastLayout.findViewById(R.id.imageViewCustomImage);
        TextView textView = toastLayout.findViewById(R.id.textViewMessage);
        image.setVisibility(View.GONE);
        textView.setGravity(Gravity.CENTER);
        textView.setText(message);
        //This line make the Tricks
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastLayout);
        toast.show();
    }

    //  Without adapter to remove the position from the list
    public void customAlert(Activity activity, List<ProductsDataSource> list, int position) {
        AlertDialog dialog;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_alert, null);
//        alertDialog.setTitle("Confirmation");
        alertDialog.setView(view);
        dialog = alertDialog.create();
        dialog.show();
        Button buttonYes = view.findViewById(R.id.buttonYes);
        Button buttonNo = view.findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    //Remove the specific item and notify the  order adapter
    public void customAlert(Activity activity, List<ProductsDataSource> list, int position, NewOrderEnqueryAdapter adapter, String action) {
        AlertDialog dialog;

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_alert, null);
        // alertDialog.setTitle("Confirmation");
        alertDialog.setView(view);
        dialog = alertDialog.create();
        dialog.show();
        //set the  dialog transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        Button buttonYes = view.findViewById(R.id.buttonYes);
        Button buttonNo = view.findViewById(R.id.buttonNo);
        TextView textView = view.findViewById(R.id.textViewRemove);
        String process = action;
        textView.setText(process);

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
        buttonYes.setOnClickListener(v -> {
            try {
                list.remove(position);
                adapter.notifyDataSetChanged();
            } catch (IndexOutOfBoundsException e) {
                e.getMessage();
            }
            if (dialog.isShowing())
                dialog.dismiss();
        });

    }

    public AlertDialog loading(Context context) {
        AlertDialog dialog;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_progress, null);

        ImageView imageView = view.findViewById(R.id.imageBar);
//        Glide.with(context)
//                .load(R.drawable.doublering100px)
//                .into(imageView);
        alertDialog.setView(view);
        dialog = alertDialog.show();
        dialog.setCancelable(false);
        Window window = dialog.getWindow();
        window.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        return dialog;
    }

    public BottomSheetDialog loadingBottom(Context context) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        dialog.setContentView(R.layout.custom_progress_bottom);
        dialog.getWindow().getAttributes().gravity = Gravity.BOTTOM;
        //set the dialog transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        return dialog;
    }

    public void dismissLoading(BottomSheetDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
