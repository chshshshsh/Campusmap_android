package shinhan.campusmap_v02.campusmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import shinhan.campusmap_v02.R;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<String> urls;
    Context context;
    //constructor
    public Adapter(ArrayList<String> ImgUrl, Context context_)
    {
        this.urls = ImgUrl;
        this.context = context_;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;

        public ViewHolder(View v)
        {
            super(v);
            image =(ImageView)v.findViewById(R.id.img);
        }

        public ImageView getImage(){ return this.image;}
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_item, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(528,410));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        Glide.with(this.context)
                .load(urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());
    }

    @Override
    public int getItemCount()
    {
        return urls.size();
    }

}