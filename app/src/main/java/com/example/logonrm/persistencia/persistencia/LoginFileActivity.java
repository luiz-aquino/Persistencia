package com.example.logonrm.persistencia.persistencia;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.jar.Manifest;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFileActivity extends AppCompatActivity {

    @BindView(R.id.etSenha_file)
    EditText etSenha;
    @BindView(R.id.etUsuario_file)
    EditText etUsuario;
    @BindView(R.id.ckbManterConectado_file)
    CheckBox ckbRemember;

    private final String SEPARADOR = "|";
    private final String FILE_NAME = "login.txt";
    private final int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_file);

        ButterKnife.bind(this);

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
        }
        else {
            ler();
        }
    }

    @OnClick(R.id.btnLogin_file)
    public void  login() {
        try {
            boolean rememberMe = ckbRemember.isChecked();

            if (rememberMe) {
                FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

                String username = etUsuario.getText().toString();
                String password = etSenha.getText().toString();

                fos.write(username.getBytes());
                fos.write(SEPARADOR.getBytes());
                fos.write(password.getBytes());
                fos.write(SEPARADOR.getBytes());
                fos.write(String.valueOf(rememberMe).getBytes());

                fos.close();
            }
            else {
                deleteFile(FILE_NAME);
            }

            finish();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void ler() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line = br.readLine();

            if (line != null) {
                String[] parts = line.split(Pattern.quote(SEPARADOR));

                etUsuario.setText(parts[0]);
                etSenha.setText(parts[1]);
                ckbRemember.setChecked(Boolean.parseBoolean(parts[2]));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CODE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ler();
            }
            else {
                Toast.makeText(this, "Permissao necessaria", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}
