package com.example.sony.cameraremote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * desc: 场景适配器
 * Author:cifz
 * time:2018/5/3 10:30
 * e_mail:wangzhen1798@gmail.com
 */

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneViewHolder> {

    private List<SceneEntity2.SceneBean> sceneList;
    private Context context;
    private SceneClick sceneClick;

    public void setSceneClick(SceneClick sceneClick) {
        this.sceneClick = sceneClick;
    }

    public SceneAdapter(List<SceneEntity2.SceneBean> sceneList, Context context) {
        this.sceneList = sceneList;
        this.context = context;
    }

    @Override
    public SceneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_scene,null);
        SceneViewHolder holder = new SceneViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SceneViewHolder holder, final int position) {
        if(holder != null){

            Glide.with(context).load(R.drawable.ic_launcher).into(holder.im_scene);
            holder.tv_scene.setText(sceneList.get(position).getName());
            holder.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sceneClick.sceneClick(sceneList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return sceneList.size();
    }

    static class SceneViewHolder extends RecyclerView.ViewHolder{
        ImageView im_scene;
        TextView tv_scene;
        RelativeLayout rl;
        public SceneViewHolder(View itemView) {
            super(itemView);
            im_scene = itemView.findViewById(R.id.im_scene);
            tv_scene = itemView.findViewById(R.id.tv_scene);
            rl = itemView.findViewById(R.id.rl);
        }
    }

}
