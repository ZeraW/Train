package com.example.train;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddTrainAdapter extends RecyclerView.Adapter<AddTrainAdapter.ViewHolder> {

    private Context context;
    private List<TrainModel> mList;

    private AddTrainAdapter.AdapterListener onClickListener;


    public AddTrainAdapter() {
    }

    public AddTrainAdapter(Context context, List<TrainModel> mList, AdapterListener onClickListener) {
        this.context = context;
        this.mList = mList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public AddTrainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_train, parent, false);
        return new AddTrainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddTrainAdapter.ViewHolder holder, final int position) {

        holder.tvNum.setText("Train No : "+mList.get(position).getTrainNo());
        holder.tvFrom.setText(mList.get(position).getFrom());
        holder.tvTo.setText(mList.get(position).getTo());
        holder.tvDepart.setText(mList.get(position).getDepartureDate());
        holder.tvArrival.setText(mList.get(position).getArrivalDate());
        holder.tvPriceA.setText("1st class : "+mList.get(position).getClassA()+"L.E");
        holder.tvPriceB.setText("2nd class : "+mList.get(position).getClassB()+"L.E");


    }

    @Override
    public int getItemCount() {
        try {
            return mList.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNum, tvFrom,tvTo,tvDepart,tvArrival,tvPriceA,tvPriceB;
        private ImageView editIV, deleteIV;
        private LinearLayout detailsLinear;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.row_num);
            tvFrom = itemView.findViewById(R.id.row_from);
            tvTo = itemView.findViewById(R.id.row_to);
            tvDepart = itemView.findViewById(R.id.row_depart);
            tvArrival = itemView.findViewById(R.id.row_return);
            tvPriceA = itemView.findViewById(R.id.row_classA);
            tvPriceB = itemView.findViewById(R.id.row_classB);


            editIV = itemView.findViewById(R.id.row_edit);
            deleteIV = itemView.findViewById(R.id.row_delete);
            detailsLinear = itemView.findViewById(R.id.row_getDetails);

            editIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.edit(v, mList.get(getAdapterPosition()));
                }
            });
            detailsLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.details(v, mList.get(getAdapterPosition()));
                }
            });
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.delete(v, mList.get(getAdapterPosition()).getId());
                }
            });

        }
    }


    public interface AdapterListener {
        void details(View v, TrainModel model);

        void edit(View v, TrainModel model);

        void delete(View v, String id);

    }


}