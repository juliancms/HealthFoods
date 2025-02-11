package com.juliancms.healthfoods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juliancms.healthfoods.model.TblProfile;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    Button SaveData;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        EditText name = (EditText) findViewById(R.id.edit_prefixsalesman);
        name.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        List<TblProfile> TblProfileList = SQLite.select().
                from(TblProfile.class).queryList();
        for (TblProfile tblprofile: TblProfileList) {
            editText = (EditText) findViewById(R.id.edit_idsalesman);
            editText.setText(tblprofile.getIdSalesMan().toString());
            editText = (EditText) findViewById(R.id.edit_id_vehicle);
            editText.setText(tblprofile.getIdVehicles().toString());
            editText = (EditText) findViewById(R.id.edit_prefixsalesman);
            editText.setText(tblprofile.getPrefixSalesMan().toString());
            editText = (EditText) findViewById(R.id.edit_namesalesman);
            editText.setText(tblprofile.getNameSalesMan().toString());
            editText = (EditText) findViewById(R.id.edit_salesconsecutive);
        }
        SaveData = (Button)findViewById(R.id.save);
        SaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<TblProfile> TblProfileList = SQLite.select().
                        from(TblProfile.class).queryList();
                for (TblProfile tblprofile: TblProfileList) {
                    tblprofile.delete();
                }
                TblProfile profile = new TblProfile();
                editText = (EditText) findViewById(R.id.edit_idsalesman);
                profile.setIdSalesMan(Integer.parseInt(editText.getText().toString()));
                editText = (EditText) findViewById(R.id.edit_id_vehicle);
                String id_vehicle = editText.getText().toString();
                editText = (EditText) findViewById(R.id.edit_prefixsalesman);
                String prefixsalesman = editText.getText().toString();
                profile.setIdVehicles(id_vehicle);
                profile.setPrefixSalesMan(prefixsalesman);
                editText = (EditText) findViewById(R.id.edit_namesalesman);
                String namesalesman = editText.getText().toString();
                profile.setNameSalesMan(namesalesman);
                if(profile.save()){
                    Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
