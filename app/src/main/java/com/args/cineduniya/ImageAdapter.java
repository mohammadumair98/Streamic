package com.args.cineduniya;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> muploads;
    private OnItemClickListener mListener;



    //to display the product images to home activity
    //this works has an adapted between the app and the firebase

    public ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        muploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);

    }


    //this part helps us to show the images in the recycler view...
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCusrrent = muploads.get(position);
        //url for text name
        holder.textViewName.setText(uploadCusrrent.getmName());
        //for movie name
        holder.textViewName_movie_name.setText(uploadCusrrent.getMovie_name());
        //for movie poster
        holder.textView_url_of_image.setText(uploadCusrrent.getmImageUrl());
        //for movie banner
        holder.Textview_banner_url.setText(uploadCusrrent.getMposterurl());
        //for movie description
        holder.TextView_description.setText(uploadCusrrent.getDescription());
        //for movie rating
        holder.Textview_rating.setText(uploadCusrrent.getRating().toString());
        //for release date
        holder.Textview_release_year.setText(uploadCusrrent.getRelease_year().toString());
        //for trailer url
        holder.Textview_trailer.setText(uploadCusrrent.getTrailer().toString());
        Glide.with(mContext)
                .load(uploadCusrrent.getmImageUrl())
                .centerInside()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener {
        public ImageView imageView;
        public LinearLayout vid_play;
        public TextView textViewName;
        public TextView textViewName_movie_name,textView_url_of_image,Textview_banner_url,TextView_description,Textview_rating,Textview_release_year,Textview_trailer;


        public ImageViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image_view_upload);
            vid_play = itemView.findViewById(R.id.onclick_linear);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewName_movie_name = itemView.findViewById(R.id.textview_name2);
            textView_url_of_image = itemView.findViewById(R.id.textview_imageurl);
            Textview_banner_url = itemView.findViewById(R.id.textview_banner_url);
            TextView_description = itemView.findViewById(R.id.textview_description);
            Textview_rating = itemView.findViewById(R.id.textview_rating);
            Textview_release_year = itemView.findViewById(R.id.textview_release_year);
            Textview_trailer = itemView.findViewById(R.id.textview_trailer);


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){


                    String vid_url = textViewName.getText().toString();
                    String vid_name = textViewName_movie_name.getText().toString();
                    String poster_url = textView_url_of_image.getText().toString();
                    String banner_url = Textview_banner_url.getText().toString();
                    String Description = TextView_description.getText().toString();
                    String trailer_url = Textview_trailer.getText().toString();
                    Float rating_movie = Float.parseFloat(Textview_rating.getText().toString());
                    Float release_year = Float.parseFloat(Textview_release_year.getText().toString());
                    mListener . OnItemClick(vid_url,vid_name,poster_url,banner_url,Description,rating_movie,release_year,trailer_url);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }



    public interface OnItemClickListener{
        //,int release_date
        void OnItemClick(String url,String mov_name,String poster_url, String banner_url, String description,Float rating,Float release_date,String trailer);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;

    }

    public void filerList(ArrayList<Upload> filterList)
    {
        try {
            muploads = filterList;
            notifyDataSetChanged();

        }catch (Exception e)
        {
            Toast.makeText(mContext, ""+e, Toast.LENGTH_SHORT).show();
        }

    }
}
