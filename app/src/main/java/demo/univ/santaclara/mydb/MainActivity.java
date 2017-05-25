package demo.univ.santaclara.mydb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String TAG_DB = "MAINACTIVITY_DB";
    dbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new dbHandler(this);
    }

    public void onClick(View view) {
        Log.d(TAG_DB, "In function onClick()");
        switch (view.getId()) {
            case R.id.add:
                EditText etName = (EditText)findViewById(R.id.nameTV);
                EditText etAge = (EditText)findViewById(R.id.ageET);

                if(etName.getText().toString().length() == 0 ) Toast.makeText(getApplicationContext(), "Please enter emplyee name ....", Toast.LENGTH_SHORT).show();
                if(etAge.getText().toString().length() == 0)  Toast.makeText(getApplicationContext(), "Please enter emplyee age ....", Toast.LENGTH_SHORT).show();
                // Inserting Contacts
                Log.d("Insert: ", "Inserting ..");
                if(etName.getText().toString().length() != 0 && etAge.getText().toString().length() != 0) {
                    db.addContact(new Contact(etName.getText().toString(), etAge.getText().toString()));
                    etName.setText(null);
                    etAge.setText(null);
                    Toast.makeText(getApplicationContext(), "Employee Recored inserted in table....", Toast.LENGTH_SHORT).show();

                }
                break;


            case R.id.delete:
                Log.d("Delete: ", "Deleting ..");
                EditText etName1 = (EditText)findViewById(R.id.nameTV);
                if(etName1.getText().toString().length() != 0) Toast.makeText(getApplicationContext(), "Please enter emplyee name ....", Toast.LENGTH_SHORT).show();

                if(etName1.getText().toString().length() != 0){
                    Contact ct = new Contact(etName1.getText().toString(), null);
                    db.getContact(etName1.getText().toString());
                    db.deleteContact(ct);
                    etName1.setText(null);
                    Toast.makeText(getApplicationContext(), "Employee Recored deleted from table....", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search:
                Log.d("Search: ", "Searching ..");
                EditText etName2 = (EditText)findViewById(R.id.nameTV);
                if(etName2.getText().toString().length() != 0) Toast.makeText(getApplicationContext(), "Please enter emplyee name ....", Toast.LENGTH_SHORT).show();

                if(etName2.getText().toString().length() != 0){
                    Contact ct = db.getContact(etName2.getText().toString());
                    if(ct.getName().length() == 0)
                        Toast.makeText(getApplicationContext(), "Employee Recored not found....", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Employee Name:" + ct.getName() + " Age:" + ct.getPhoneNumber(), Toast.LENGTH_SHORT).show();
                }

        }
    }
}
