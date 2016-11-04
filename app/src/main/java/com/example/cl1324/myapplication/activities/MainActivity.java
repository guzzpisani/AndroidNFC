package com.example.cl1324.myapplication.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cl1324.myapplication.R;
import com.example.cl1324.myapplication.util.Hex.Hex;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView tvContador;

    private NfcAdapter mNfcAdapter;

    private String tagReceived = null;
    private boolean stillThere = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnAdicionar = (Button) findViewById(R.id.btn_adicionar);
        tvContador = (TextView) findViewById(R.id.tv_contador);

        configurarBotoes();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mNfcAdapter == null) {
            Toast.makeText(this, "Ative o NFC", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    private void configurarBotoes() {
//        btnAdicionar.setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);


        tvContador.setText("Pegou a tag: " + Hex.hexToString(tag.getId()));

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if(tag != null) {
            Log.i(TAG, "onNewIntent: Recebemos um novo intent");
            Toast.makeText(this, "Detectado: " + Hex.hexToString(tag.getId()), Toast.LENGTH_SHORT).show();

            tvContador.setText(Hex.hexToString(tag.getId()));
            if (tagReceived == null){
                tagReceived = Hex.hexToString(tag.getId());
            }else{
                mNfcAdapter.disableReaderMode;
                tvContador.setText("Desativou o NFC");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();



        Intent it = new Intent(this, MainActivity.class);
        it.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pit = PendingIntent.getActivity(this, 0, it, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        mNfcAdapter.enableForegroundDispatch(this, pit, intentFilter, null);

        if(getIntent() != null) {
            onNewIntent(getIntent());
        }
    }

    @Override
    public void onClick(View v) {
//        if(v.getId() == R.id.btn_adicionar) {
//            tvContador.setText(String.valueOf(contador++));
//            Log.i(TAG, "onClick: " + contador);
//        }
    }
}
