package br.com.jortec.jogodamemoria.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.List;

import br.com.jortec.jogodamemoria.R;
import br.com.jortec.jogodamemoria.interfaces.RecyclerViewOnclickListener;

/**
 * Created by Jorliano on 11/10/2015.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    private int[] lista;
    private LayoutInflater inflater;
    RecyclerViewOnclickListener recyclerViewOnclickListener;


    public RecycleAdapter(Context c, int[] l) {
        lista = l;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // View v = inflater.inflate(R.layout.item_moto, viewGroup, false);
        View v = inflater.inflate(R.layout.item_recycler, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

       viewHolder.imagem.setImageResource(lista[position]);
       // viewHolder.imagem.setImageResource(R.drawable.card);
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }

    public void setRecyclerViewOnclickListener (RecyclerViewOnclickListener r){
        recyclerViewOnclickListener = r;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imagem;

        public MyViewHolder(View itemView) {
            super(itemView);
            imagem = (ImageView) itemView.findViewById(R.id.imagem);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewOnclickListener != null){
                recyclerViewOnclickListener.onclickListener(v, getPosition());
            }
        }
    }
}






