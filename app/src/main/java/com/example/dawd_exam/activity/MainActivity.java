package com.example.dawd_exam.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dawd_exam.R;
import com.example.dawd_exam.database.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edName;
    private EditText edQuantity;
    private Button btAdd;
    private Button btView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        db = new DBHelper(this);
        db.getReadableDatabase();
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edQuantity = (EditText) findViewById(R.id.edQuantity);
        btAdd = (Button) findViewById(R.id.btAdd);
        btView= (Button) findViewById(R.id.btView);

        btAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btAdd){
            onAddProduct();
        }
    }

    private void onAddProduct() {
        if(edName.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter username  ",Toast.LENGTH_LONG).show();
            return;
        }
        if(edQuantity.getText().toString().isEmpty()){
            Toast.makeText(this,"Please enter quantity  ",Toast.LENGTH_LONG).show();
            return;
        } else {
            Integer.parseInt(edQuantity.getText().toString());
        }
        String isAdd = db.addProduct(edName.getText().toString(), Integer.parseInt(edQuantity.getText().toString()));
        Toast.makeText(this,isAdd,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this,ListProductAct.class);
        startActivity(intent);
        }
}