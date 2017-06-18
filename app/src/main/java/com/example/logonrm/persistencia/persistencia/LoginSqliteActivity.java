package com.example.logonrm.persistencia.persistencia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.logonrm.persistencia.persistencia.Models.Login;
import com.example.logonrm.persistencia.persistencia.services.LoginDAO;

public class LoginSqliteActivity extends AppCompatActivity {

    private LoginDAO loginDAO;
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox ckbManterLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sqlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText) findViewById(R.id.etUsuarioSQlite);
        etPassword = (EditText) findViewById(R.id.etSenhaSqlite);
        ckbManterLogado = (CheckBox) findViewById(R.id.ckbManterConectadoSQLite);
        loginDAO = new LoginDAO(this);

        ler();
    }

    public void login(View v){
        loginDAO.deleteAll();
        if(ckbManterLogado.isChecked()){
            Login l = new Login();
            l.setCodUser(1);
            l.setUsername(etUsername.getText().toString());
            l.setPassword(etPassword.getText().toString());
            loginDAO.insert(l);
        }
        finish();
    }

    public void ler(){
        Login l = loginDAO.getById(1);

        if(l != null){
            etUsername.setText(l.getUsername());
            etPassword.setText(l.getPassword());
            ckbManterLogado.setChecked(true);
        }
    }
}
