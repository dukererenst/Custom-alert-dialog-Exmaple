package krunal.com.example.customalertdialog;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog malertDialog;

    String[] ItemsList;
    boolean[] checkedItems;
    // values for Radio button.
    CharSequence[] values = {" Car "," Bike"," Cat "};
    ArrayList<Integer> mSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemsList = getResources().getStringArray(R.array.shopping_item);
        checkedItems = new boolean[ItemsList.length];

        TextView getResult = findViewById(R.id.textViewItems);
        Button showAlertDialog = findViewById(R.id.buttonShowAlertDialog);

        Button checkBoxButton = findViewById(R.id.buttonCheckBaxAlertDialog);

        Button radioAlertDialog = findViewById(R.id.buttonRadioButtonAlertDialog);

        showAlertDialog.setOnClickListener(v -> {

            AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            @SuppressLint("InflateParams") View mCustomView = getLayoutInflater().inflate(R.layout.dialog_layout, null);
            final EditText mEditEmail = mCustomView.findViewById(R.id.editTextEmail);
            final EditText mEditPassword = mCustomView.findViewById(R.id.editTextPassword);
            Button mButtonLogin = mCustomView.findViewById(R.id.buttonLogin);

            mAlertDialogBuilder.setView(mCustomView);
            final AlertDialog dialog = mAlertDialogBuilder.create();
            dialog.show();

            mButtonLogin.setOnClickListener(v1 -> {
                if (!mEditEmail.getText().toString().isEmpty() && !mEditPassword.getText().toString().isEmpty()){
                    Toast.makeText(this,"Login Successful",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(this,"Enter Email and Password!",Toast.LENGTH_LONG).show();
                }

            });


        });

        checkBoxButton.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setTitle(R.string.dialog_title);
            mBuilder.setMultiChoiceItems(ItemsList, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                    if(isChecked){
                        mSelectedItems.add(position);
                    }else{
                        mSelectedItems.remove((Integer.valueOf(position)));
                    }
                }
            });

            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    String item = "";
                    for (int i = 0; i < mSelectedItems.size(); i++) {
                        item = item + ItemsList[mSelectedItems.get(i)];
                        if (i != mSelectedItems.size() - 1) {
                            item = item + ", ";
                        }
                    }
                    getResult.setText(item);
                }
            });

            mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    for (int i = 0; i < checkedItems.length; i++) {
                        checkedItems[i] = false;
                        mSelectedItems.clear();
                        getResult.setText("");
                    }
                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();

        });

        radioAlertDialog.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Select Your Choice");

            builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    switch(item)
                    {
                        case 0:

                            Toast.makeText(MainActivity.this, "Car Clicked", Toast.LENGTH_LONG).show();
                            break;
                        case 1:

                            Toast.makeText(MainActivity.this, "Bike Item Clicked", Toast.LENGTH_LONG).show();
                            break;
                        case 2:

                            Toast.makeText(MainActivity.this, "Cat Item Clicked", Toast.LENGTH_LONG).show();
                            break;
                    }
                    malertDialog.dismiss();
                }
            });
            malertDialog = builder.create();
            malertDialog.show();
        });

    }
}
