package com.example.delete2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCAdditionalInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCCustomerInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCEMITransactionInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCProductInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCShipmentInfoInitializer;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

public class MainActivity extends AppCompatActivity implements SSLCTransactionResponseListener {

    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);

        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("customer name", "customer email",
                "address", "dhaka", "1214", "Bangladesh","123456");

        final SSLCEMITransactionInitializer emiTransactionInitializer = new SSLCEMITransactionInitializer(1);

        final SSLCProductInitializer productInitializer = new SSLCProductInitializer ("food", "food",
                new SSLCProductInitializer.ProductProfile.TravelVertical("Travel", "10",
                        "A", "12", "Dhk-Syl"));

        final SSLCShipmentInfoInitializer shipmentInfoInitializer = new SSLCShipmentInfoInitializer ("Courier",
                2, new SSLCShipmentInfoInitializer.ShipmentDetails("AA","Address 1",
                "Dhaka","1000","BD"));



        IntegrateSSLCommerz
                .getInstance(context)
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addEMITransactionInitializer(emiTransactionInitializer)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .addProductInitializer(productInitializer)
                .buildApiCall(MainActivity.this);

    }

    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {

        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void transactionFail(String s) {
        Toast.makeText(this, " "+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void merchantValidationError(String s) {
        Toast.makeText(this, " "+s, Toast.LENGTH_SHORT).show();
    }

    public void pay(View view) {

        String amou = amount.getText().toString();

        Double total = Double.parseDouble(amou);

        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization ("Your Store ID","Your Store Password", total, SSLCCurrencyType.BDT,"ajbdsncncvlk", "Fees", SSLCSdkType.TESTBOX);


        final SSLCCustomerInfoInitializer customerInfoInitializer = new SSLCCustomerInfoInitializer("Shafi", "abdullah_al.shafi@outlook.com",
                "address", "dhaka", "1200", "Bangladesh", "123456789");

        final SSLCAdditionalInitializer additionalInitializer = new SSLCAdditionalInitializer();
        additionalInitializer.setValueA("10");


        IntegrateSSLCommerz
                .getInstance(view.getContext())
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .addCustomerInfoInitializer(customerInfoInitializer)
                .buildApiCall(this);
    }
}

// READ ME
/*
# SSLCOMMERZ
Integrate SSLCOMMERZ with Android

Here I am sharing the steps to incorporate with SSLCOMMERZ. It is the faster and hassle free one -

## Test/Sandbox Environment

Step 1 : Create a Sandbox / Test Account or get the API/Guidelines from this link - https://developer.sslcommerz.com/ also you may check our ready Payment Plugin and Libraries (opencart, magento, php, java, laravel, SDK - Android, IOS etc) from this link - https://github.com/sslcommerz

Step 2 : Integrate Test ID to your system/website (you'll get all the credentials instantly over your registered email) After successfully integrated in Test Environment then you need live/Production ID.

## Live/Production Environment

Step 1 : Create a live account from this link with Filling-up info - http://signup.sslcommerz.com/register (after fill up the form you’ll be asked for upload Required Document: Trade License copy, NID copy, Incorporation Certificate (if any) eTIN copy etc.

Step 2 : Replace live credential to sandbox account and change environment to live account (you'll get all the credentials instantly over your registered email). After successfully replace live ID you are in Live Environment! Now user can pay by their VISA, MASTER and Mobile/Internet banking.

## Here I am Integrate SSLCOMMERZ with Android (Test/Sandbox Environment)


### Step 1 :Users need to add below dependency to their build.gradle (app module) :

    implementation 'com.android.support:support-compat:28.0.0'
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:design:28.0.0'
    implementation 'com. google. code. gson: gson:2.8.5'


#### If users project supports androidx artifacts

    implementation 'androidx.core:core:1.0.0'
    implementation 'androidx.appcompat:appcompat:1.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'com.google.code.gson:gson:2.8.5'

### Step 2 :To insert the Dependency Mentioned in step 1, users need to follow the below steps:

From top left corner of project pane,
“Select” Project  🡪 app 🡪 libs 🡪 “Paste” sslCommerzSdk.aar in libs folder

Afterwards, users need to additional codes to the project after inserting sslCommerzSdk.aar in libs folder by following steps below:

 “Select” Project app 🡪 build.gradle (app module) and add below code and sync project:

    repositories {
        flatDir {
         dirs 'libs'
        }
     }
    implementation(name: 'sslCommerzSdk', ext: 'aar')


![ssl1](https://user-images.githubusercontent.com/32819843/81905418-7b304880-95e6-11ea-8350-f9196d641a7d.png)

![ssl2](https://user-images.githubusercontent.com/32819843/81905461-8be0be80-95e6-11ea-9125-ea1d4a4d7377.png)


### Step 3 :

After successfully completing SDK integration, users can request for transaction.  In order to commence transaction, users need to pass SSLCommerzInitialization.

#### SSLCommerzInitialization (Mandatory to initiate SSL Commerz):



        final SSLCommerzInitialization sslCommerzInitialization = new SSLCommerzInitialization
                ("Your Store ID","Your Store Password", total, SSLCCurrencyType.BDT,
                        "ajbdsncncvlk", "Fees", SSLCSdkType.TESTBOX);


#### Afterwards, user need to call below class to connect with SSLCommerz:



        IntegrateSSLCommerz
                .getInstance(view.getContext())
                .addSSLCommerzInitialization(sslCommerzInitialization)
                .buildApiCall(this);



<div align="center">

# Thank You
</div>
*/
