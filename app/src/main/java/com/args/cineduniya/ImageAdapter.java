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
        holder.textViewName.setText(uploadCusrrent.getmName());
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
            View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener{
        public ImageView imageView;
        public LinearLayout vid_play;
        public TextView textViewName;


        public ImageViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.image_view_upload);
            vid_play = itemView.findViewById(R.id.onclick_linear);
            textViewName = itemView.findViewById(R.id.textview_name);


            imageView.setOnClickListener(this);




        }


        @Override
        public void onClick(View v) {

            // to handle clicks
            if (mListener != null) {
                final int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    mListener.OnItemClick(position);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            String video_url= textViewName.getText().toString();
                            mListener.place_order(position,video_url);


                        }
                    });





                }
            }


        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            return false;
        }
    }

        //items
        public interface OnItemClickListener {


            //to send the data to the home activity...
            void place_order(int position, String url);


            void OnItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener)
            {

            mListener = listener;
    }
}
