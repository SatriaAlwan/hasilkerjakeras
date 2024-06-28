package com.example.hasilkerjakeras.order;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.hasilkerjakeras.R;
import com.example.hasilkerjakeras.utils.FunctionHelper;
import com.google.android.material.button.MaterialButton;

public class OrderActivity extends AppCompatActivity {

    public static final String DATA_TITLE = "TITLE";
    String strTitle;
    int paket1 = 80000, paket2 = 94000, paket3 = 113700, paket4 = 102500;
    int itemCount1 = 0, itemCount2 = 0, itemCount3 = 0, itemCount4 = 0;
    int countP1, countP2, countP3, countP4, countP5, countP6, totalItems, totalPrice;
    ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5, imageAdd6,
            imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5, imageMinus6;
    Toolbar toolbar;
    TextView tvPaket1, tvPaket2, tvPaket3, tvPaket4, tvPaket5, tvPaket6,
            tvPaket11, tvJumlahPorsi, tvTotalPrice;
    MaterialButton btnCheckout;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setStatusbar();
        setInitLayout();
        setPaket1();
        setPaket2();
        setPaket3();
        setPaket4();
        setInputData();
    }

    private void setInitLayout() {
        tvPaket11 = findViewById(R.id.tvPaket11);
        toolbar = findViewById(R.id.toolbar);
        tvPaket1 = findViewById(R.id.tvPaket1);
        tvPaket2 = findViewById(R.id.tvPaket2);
        tvPaket3 = findViewById(R.id.tvPaket3);
        tvPaket4 = findViewById(R.id.tvPaket4);
        tvJumlahPorsi = findViewById(R.id.tvJumlahPorsi);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        btnCheckout = findViewById(R.id.btnCheckout);

        strTitle = getIntent().getExtras().getString(DATA_TITLE);
        if (strTitle != null) {

            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(strTitle);
        }

        tvPaket11.setPaintFlags(tvPaket11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
    }

    private void setPaket1() {
        imageAdd1.setOnClickListener(v -> {
            itemCount1 = itemCount1 + 1;
            tvPaket1.setText(String.valueOf(itemCount1));
            countP1 = paket1 * itemCount1;
            setTotalPrice();
        });

        imageMinus1.setOnClickListener(v -> {
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1;
                tvPaket1.setText(String.valueOf(itemCount1));
            }
            countP1 = paket1 * itemCount1;
            setTotalPrice();
        });
    }

    private void setPaket2() {
        imageAdd2.setOnClickListener(v -> {
            itemCount2 = itemCount2 + 1;
            tvPaket2.setText(String.valueOf(itemCount2));
            countP2 = paket2 * itemCount2;
            setTotalPrice();
        });

        imageMinus2.setOnClickListener(v -> {
            if (itemCount2 > 0) {
                itemCount2 = itemCount2 - 1;
                tvPaket2.setText(String.valueOf(itemCount2));
            }
            countP2 = paket2 * itemCount2;
            setTotalPrice();
        });
    }

    private void setPaket3() {
        imageAdd3.setOnClickListener(v -> {
            itemCount3 = itemCount3 + 1;
            tvPaket3.setText(String.valueOf(itemCount3));
            countP3 = paket3 * itemCount3;
            setTotalPrice();
        });

        imageMinus3.setOnClickListener(v -> {
            if (itemCount3 > 0) {
                itemCount3 = itemCount3 - 1;
                tvPaket3.setText(String.valueOf(itemCount3));
            }
            countP3 = paket3 * itemCount3;
            setTotalPrice();
        });
    }

    private void setPaket4() {
        imageAdd4.setOnClickListener(v -> {
            itemCount4 = itemCount4 + 1;
            tvPaket4.setText(String.valueOf(itemCount4));
            countP4 = paket4 * itemCount4;
            setTotalPrice();
        });

        imageMinus4.setOnClickListener(v -> {
            if (itemCount4 > 0) {
                itemCount4 = itemCount4 - 1;
                tvPaket4.setText(String.valueOf(itemCount4));
            }
            countP4 = paket4 * itemCount4;
            setTotalPrice();
        });
    }

    private void setTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4;
        totalPrice = countP1 + countP2 + countP3 + countP4 + countP5 + countP6;

        tvJumlahPorsi.setText(totalItems + " items");
        tvTotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }

    private void setInputData() {
        btnCheckout.setOnClickListener(v -> {
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(OrderActivity.this, "eiittss, tidak bisa",
                        Toast.LENGTH_SHORT).show();
            } else if (totalItems < 1) {
                Toast.makeText(OrderActivity.this, "pilih dulu",
                        Toast.LENGTH_SHORT).show();
            } else {
                orderViewModel.addDataOrder(strTitle, totalItems, totalPrice);
                Toast.makeText(OrderActivity.this,
                        "pesanan menu anda sudah dipilih, cek di booking pemesanan ya!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void setStatusbar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(@NonNull Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Dapatkan instance ViewModel
            OrderViewModel myViewModel = new ViewModelProvider(this).get(OrderViewModel.class);

            // Gunakan ViewModel Anda di sini
            // Misalnya, amati LiveData dari ViewModel
            myViewModel.getSomeLiveData().observe(this, data -> {
                // Perbarui UI dengan data
            });
        }
    }
}
