package com.nacho.tame.eltiempo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private Context context;

    private List<Forecast> forecastList = new ArrayList<>();
    private LayoutInflater layoutInflater;


    public ForecastAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder {

        public final TextView txtDate;
        public final TextView txtWindDirection;
        public final TextView txtWindSpeed;
        public final TextView txtMaxTemp;
        public final TextView txtMinTemp;
        public final TextView txtCurrentTemp;
        public final ImageView imgState;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtWindDirection = itemView.findViewById(R.id.txtWindDirection);
            txtWindSpeed = itemView.findViewById(R.id.txtWindSpeed);
            txtMaxTemp = itemView.findViewById(R.id.txtMaxTemp);
            txtMinTemp = itemView.findViewById(R.id.txtMinTemp);
            txtCurrentTemp = itemView.findViewById(R.id.txtCurrentTemp);

            imgState = itemView.findViewById(R.id.imgState);
        }
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.forecast_item, viewGroup, false);
        return new ForecastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder vh, int position) {
        Forecast forecast = forecastList.get(position);

        vh.txtDate.setText(forecast.getApplicable_date());
        vh.txtMaxTemp.setText(getTwoDecimalNumbers(forecast.getMax_temp())+" ºC");
        vh.txtMaxTemp.setTextColor(Color.RED);
        vh.txtMinTemp.setText(getTwoDecimalNumbers(forecast.getMin_temp())+" ºC");
        vh.txtMinTemp.setTextColor(Color.BLUE);
        vh.txtCurrentTemp.setText(getTwoDecimalNumbers(forecast.getThe_temp())+" ºC");
        vh.txtWindDirection.setText(forecast.getWind_direction_compass());
        vh.txtWindSpeed.setText(getTwoDecimalNumbers(forecast.getWind_speed())+ " km/h");

        GlideApp.with(context)
                .load("https://www.metaweather.com/static/img/weather/png/"+forecast.getWeather_state_abbr()+".png")
                .fitCenter()
                .into(vh.imgState);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public String getTwoDecimalNumbers(float number){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        return df.format(number);
    }

    public void setData(List<Forecast> newList){
        forecastList = newList;
        notifyDataSetChanged();
    }
}
