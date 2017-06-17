package com.example.logonrm.persistencia.persistencia.services;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.logonrm.persistencia.persistencia.Models.Contact;
import com.example.logonrm.persistencia.persistencia.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Luiz Aquino on 2017-06-17.
 */

public class ContactAdaptor extends BaseAdapter {

    private Context context;
    private List<Contact> contacts;

    public ContactAdaptor(Context context, List<Contact> contacts){
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return this.contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.contact_item_layout, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvNameItem);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhoneItem);

        tvName.setText(contacts.get(position).getName());
        tvPhone.setText(contacts.get(position).getTelefone());

        return convertView;
    }
}
