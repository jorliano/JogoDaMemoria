package br.com.jortec.jogodamemoria.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Jorliano on 28/10/2015.
 */
public class GridAdapter extends BaseAdapter {
    private Context ctx;
    private int[] lista;

    public GridAdapter(Context ctx, int[] lista){
        this.ctx = ctx;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.length;
    }

    @Override
    public Object getItem(int position) {
        return lista[position];
    }

    @Override
    public long getItemId(int position) {
        return lista[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Criar componente imagemView
        ImageView im = new ImageView(ctx);
        im.setImageResource(lista[position]);
        im.setAdjustViewBounds(true);
        return im;
    }
}
