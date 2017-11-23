package com.example.york.teamcraft.teammanage.board.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/9/11.
 */

public class BoardItemAdapter extends RecyclerView.Adapter<BoardItemAdapter.ViewHolder>{
    private static String TAG = "BoardItemAdapter";

    private Context context;
    private View.OnClickListener listener;
    private ArrayList<Post> dataList;  // 存放TeamPosts的List

    public BoardItemAdapter(Context context, ArrayList<Post> list, View.OnClickListener listener) {
        this.context = context;
        this.dataList = list;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtFavNum;
        private TextView txtDate;
        private TextView txtTime;
        private CircleImageView cirImgView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_card_item_title);
            txtFavNum = (TextView) itemView.findViewById(R.id.txt_card_item_favorite_number);
            txtDate = (TextView) itemView.findViewById(R.id.txt_card_item_post_date);
            txtTime = (TextView) itemView.findViewById(R.id.txt_card_item_post_time);
            cirImgView = (CircleImageView) itemView.findViewById(R.id.cir_img_poster);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_cardview_item, parent, false);
        v.setOnClickListener(listener);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = dataList.get(position).getContent();
        String date = dataList.get(position).getDate();
        String time = dataList.get(position).getTime();
        int favorite = dataList.get(position).getFavoriteNum();
        String imgUrl = dataList.get(position).getImageUrl();

        holder.txtTitle.setText(title);
        holder.txtFavNum.setText(Integer.toString(favorite));
        holder.txtDate.setText(date);
        holder.txtTime.setText(time);
        Picasso.with(context)
                .load(imgUrl)
                .into(holder.cirImgView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
