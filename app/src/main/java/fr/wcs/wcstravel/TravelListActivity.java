package fr.wcs.wcstravel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TravelListActivity extends AppCompatActivity {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference().child("checkpoint5/travels");

    private ArrayList<TravelModel> travelList = new ArrayList<>();

    private String travelDetails;
    private String returnDateAsked;
    private String departureDateAsked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);

        departureDateAsked = getIntent().getStringExtra("datedeparture");
        returnDateAsked = getIntent().getStringExtra("datereturn");
        travelDetails = getIntent().getStringExtra("citydeparture")+"_"+getIntent().getStringExtra("citydestination");

        addTravel();

    }

    private void addTravel(){

        //Récupérer la liste des travels
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        TravelModel travelObject = postSnapshot.getValue(TravelModel.class);
                        String travelName = travelObject.getTravel();
                        String airline = travelObject.getAirline();
                        String departDate = travelObject.getDeparture_date();
                        String returnDate = travelObject.getReturn_date();
                        String travelPrice = travelObject.getPrice();
                        if (travelName.equals(travelDetails)
                                &(departDate.equals(departureDateAsked))
                                &(returnDate.equals(returnDateAsked))) {
                            travelList.add(travelObject);
                        }

                    }

                    final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_travel_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TravelListActivity.this));
                    recyclerView.setAdapter(new RecyclerAdapter(TravelListActivity.this, travelList));
                }else {
                    Toast.makeText(TravelListActivity.this, "No matches", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled (DatabaseError error){
            }
        });


    }
}
