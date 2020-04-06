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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        Picasso.get()
                .load(uploadCusrrent.getmImageUrl())
                .fit()
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
        public TextView textViewName_movie_name,textView_url_of_image,Textview_banner_url,TextView_description;


        public ImageViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image_view_upload);
            vid_play = itemView.findViewById(R.id.onclick_linear);
            textViewName = itemView.findViewById(R.id.textview_name);
            textViewName_movie_name = itemView.findViewById(R.id.textview_name2);
            textView_url_of_image = itemView.findViewById(R.id.textview_imageurl);
            Textview_banner_url = itemView.findViewById(R.id.textview_banner_url);
            TextView_description = itemView.findViewById(R.id.textview_description);


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
                    mListener . OnItemClick(vid_url,vid_name,poster_url,banner_url,Description);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }
    }



    public interface OnItemClickListener{
        void OnItemClick(String url,String mov_name,String poster_url, String banner_url, String description);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;

    }
}
