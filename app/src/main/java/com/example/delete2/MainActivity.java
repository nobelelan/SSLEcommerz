package com.example.delete2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("customer name", "customer email",
                "address", "dhaka", "1214", "Bangladesh","123456");
    }
}