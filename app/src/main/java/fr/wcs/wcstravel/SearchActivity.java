package fr.wcs.wcstravel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList COUNTRIES = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("checkpoint5/airports");

        final AutoCompleteTextView departurecity = (AutoCompleteTextView) findViewById(R.id.edittxt_departurename);
        final AutoCompleteTextView destinationcity = (AutoCompleteTextView) findViewById(R.id.edittxt_destinationname);
        final EditText departuredate = (EditText)findViewById(R.id.edittxt_departuredate);
        final EditText returndate = (EditText)findViewById(R.id.edittxt_returndate);
        final Button searchButton = (Button)findViewById(R.id.searchflightsbutton);


        //Récupérer la liste des airports
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        AirportModel airportModel = postSnapshot.getValue(AirportModel.class);
                        String city = airportModel.getValue();
                        COUNTRIES.add(city);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(SearchActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
        });

        //Afficher la liste dans autocomplete textview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        departurecity.setAdapter(adapter);
        destinationcity.setAdapter(adapter);


        //Date Picker Départ
        departuredate.setFocusable(false);

        final Calendar myCalendarDepart = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final DatePickerDialog.OnDateSetListener datePickerDeparture = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarDepart.set(Calendar.YEAR, year);
                myCalendarDepart.set(Calendar.MONTH, monthOfYear);
                myCalendarDepart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String departDate = sdf.format(myCalendarDepart.getTime());

                departuredate.setText(departDate);
            }
        };

        departuredate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchActivity.this, datePickerDeparture,
                        myCalendarDepart.get(Calendar.YEAR),
                        myCalendarDepart.get(Calendar.MONTH),
                        myCalendarDepart.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Date picker Retour
        returndate.setFocusable(false);

        final Calendar myCalendarRetour = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener datePickerReturn = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendarRetour.set(Calendar.YEAR, year);
                myCalendarRetour.set(Calendar.MONTH, monthOfYear);
                myCalendarRetour.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String returnDate = sdf.format(myCalendarRetour.getTime());

                returndate.setText(returnDate);
            }
        };

        returndate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(SearchActivity.this, datePickerReturn,
                        myCalendarRetour.get(Calendar.YEAR),
                        myCalendarRetour.get(Calendar.MONTH),
                        myCalendarRetour.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((returndate.getText().toString().isEmpty())
                        ||(departuredate.getText().toString().isEmpty())
                        ||(departurecity.equals(""))
                        ||(destinationcity.equals(""))) {
                    Toast.makeText(SearchActivity.this, R.string.toast_empty_form,
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(SearchActivity.this, TravelListActivity.class);
                    intent.putExtra("datedeparture", departuredate.getText().toString());
                    intent.putExtra("datereturn", returndate.getText().toString());
                    intent.putExtra("citydeparture", departurecity.getText().toString());
                    intent.putExtra("citydestination", destinationcity.getText().toString());
                    startActivity(intent);
                    }
            }
        });
    }
}
