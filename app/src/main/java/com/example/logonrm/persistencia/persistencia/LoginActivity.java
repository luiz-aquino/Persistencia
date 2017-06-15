package com.example.logonrm.persistencia.persistencia;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @BindView(R.id.etUsuario)
    EditText etUsuario;
    @BindView(R.id.etSenha)
    EditText etSenha;

    @BindView(R.id.ckbManterConectado)
    CheckBox ckbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        preferences = getPreferences(MODE_PRIVATE);

        ler();
    }

    @OnClick(R.id.btnLogin)
    public void logar(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        if(ckbManterConectado.isChecked()) {
            e.putString("usuario", etUsuario.getText().toString());
            e.putString("senha", etSenha.getText().toString());
            e.putBoolean("manterConectado", ckbManterConectado.isChecked());
        }
        else{
            e.putString("usuario", "");
            e.putString("senha", "");
            e.putBoolean("manterConectado", false);
        }
        e.apply();
        finish();
    }

    private void ler(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String username = sp.getString("usuario", "");
        String senha = sp.getString("senha", "");
        boolean manterConectado = sp.getBoolean("manterConectado", false);
        etUsuario.setText(username);
        etSenha.setText(senha);
        ckbManterConectado.setChecked(manterConectado);
    }
}
