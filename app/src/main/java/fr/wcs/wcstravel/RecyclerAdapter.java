package fr.wcs.wcstravel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private View mView;
    private ArrayList<TravelModel> items;

    //public constructor
    public RecyclerAdapter(Context context, ArrayList<TravelModel> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        mView = inflater.inflate(R.layout.travel_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final TravelModel item = items.get(position);
        holder.travelName.setText(mView.getContext().getString(R.string.your_travel)+item.getTravel());
        holder.departureDate.setText(mView.getContext().getString(R.string.your_departure_date)+item.getDeparture_date());
        holder.returnDate.setText(mView.getContext().getString(R.string.your_return_date)+item.getReturn_date());
        if (item.getCurrency().equals("USD")){
            holder.price.setText(mView.getContext().getString(R.string.dollar)+item.getPrice());
        }else
        {
            holder.price.setText(item.getPrice()+mView.getContext().getString(R.string.euros));
        }
        holder.airline.setText(mView.getContext().getString(R.string.airline_name)+item.getAirline());

        /*mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

    public void changeCurrency(){

        for (TravelModel travelModel : items) {
            items.remove(travelModel);
            items.add(convertPrice(travelModel));
        }
        notifyDataSetChanged();
    }

    private TravelModel convertPrice(TravelModel newTravelModel) {
        if (newTravelModel.getCurrency().equals("EUR")){
            newTravelModel.setPrice(String.valueOf(Math.floor((Double.parseDouble(newTravelModel.getPrice()) / 0.805581066)*100)/100));
            newTravelModel.setCurrency("USD");
        }else {
            newTravelModel.setPrice(String.valueOf(Math.floor((Double.parseDouble(newTravelModel.getPrice()) * 0.805581066)*100)/100));
            newTravelModel.setCurrency("EUR");
        }
        return newTravelModel;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView travelName;
        private final TextView departureDate;
        private final TextView returnDate;
        private final TextView price;
        private final TextView airline;

        public MyViewHolder(final View itemView) {
            super(itemView);

            travelName = (TextView)itemView.findViewById(R.id.textViewTravelName);
            departureDate = (TextView)itemView.findViewById(R.id.textViewDepartureDate);
            returnDate = (TextView)itemView.findViewById(R.id.textViewReturnDate);
            price = (TextView)itemView.findViewById(R.id.textViewPrice);
            airline = (TextView)itemView.findViewById(R.id.textViewAirline);
        }
    }

}
