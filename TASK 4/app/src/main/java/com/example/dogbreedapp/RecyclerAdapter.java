package com.example.dogbreedapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    String names[];
    Context context;
    String url;

    public RecyclerAdapter(MainActivity context, String[] names) {
        //System.out.println(url);
        //System.out.println(names);
        this.url = "";
        this.names = names;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_recyclerview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ImageApi imageApi = retrofit.create(ImageApi.class);
        Call<Images> call = imageApi.getImage(names[position]);
        call.enqueue(new Callback<Images>() {
            @Override
            public void onResponse(Call<Images> call, Response<Images> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Code: " + response.code());
                    return;
                }
                Images images = response.body();
                url = images.getUrl();
                Picasso.with(context).load(url).into(holder.imageView);
                System.out.println("Inside "+url);
            }

            @Override
            public void onFailure(Call<Images> call, Throwable t) {
                System.out.println("Error : " + t.toString());
            }
        });
        holder.breed.setText(names[position]);
        System.out.println(names[position]);
        System.out.println(url);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView breed;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            breed = itemView.findViewById(R.id.breed);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
