package com.example.logonrm.persistencia.persistencia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.logonrm.persistencia.persistencia.Models.Contact;
import com.example.logonrm.persistencia.persistencia.services.ContactAdaptor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {

    ListView lvContatos;
    private List<Contact> contacts;

    private final int REQUEST_PERMISSION_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvContatos = (ListView) findViewById(R.id.lvContatos);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[] { Manifest.permission.READ_CONTACTS }, REQUEST_PERMISSION_CODE);
        }
        else {
            readContacts();
        }
    }


    private void readContacts(){
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if(phones.getCount() > 0){
            contacts = new ArrayList<>();
            phones.moveToFirst();
            do {
                String nome = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String telefone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact();
                contact.setName(nome);
                contact.setTelefone(telefone);
                contacts.add(contact);
            }while (phones.moveToNext());

            lvContatos.setAdapter(new ContactAdaptor(this, contacts));

            lvContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("telefone", contacts.get(position).getTelefone());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_PERMISSION_CODE)
        {
            readContacts();
        }
        else {
            Toast.makeText(this, "Permissao necessaria", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
