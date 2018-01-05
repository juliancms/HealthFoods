package com.juliancms.healthfoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.filebrowser.Constants;
import com.aditya.filebrowser.FileChooser;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;

public class SettingsActivity extends AppCompatActivity {
    Button EditTblUM;
    TextView _tvEditTblUM;
    private static final int PICK_FILE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        EditTblUM = (Button)findViewById(R.id.edit_tblum);
        _tvEditTblUM = (TextView) findViewById(R.id.file_tblum);
        EditTblUM.setOnClickListener(new View.OnClickListener() {
            private String _path;
            @Override
            public void onClick(View view) {
//                final Context ctx = SettingsActivity.this;
//                new ChooserDialog().with(ctx)
//                        .withStartFile(_path)
//                        .withChosenListener(new ChooserDialog.Result() {
//                            @Override
//                            public void onChoosePath(String path, File pathFile) {
//                                Toast.makeText(ctx, "FILE: " + path, Toast.LENGTH_SHORT).show();
//                                _path = path;
//                                _tvEditTblUM.setText(_path);
//                            }
//                        })
//                        .build()
//                        .show();
                Intent i2 = new Intent(getApplicationContext(), FileChooser.class);
                i2.putExtra(Constants.SELECTION_MODE,Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal());
                startActivityForResult(i2,PICK_FILE_REQUEST);
            }

        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == PICK_FILE_REQUEST && data!=null) {
            if (resultCode == RESULT_OK) {
                proImportCSV(new File(data.getData().getPath()));
            }
        }
    }
    private void proImportCSV(File from){
        try{
            CSVReader reader = new CSVReader(new FileReader(from));
            String [] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                for(int i=1;i<=87;i+=1)
                {
                    System.out.println(i + " " + nextLine[i] + " ");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }
    }
}
