package br.com.jortec.jogodamemoria.interfaces;

import android.view.View;

/**
 * Created by Jorliano on 14/12/2015.
 */
public interface RecyclerViewOnclickListener {
    public void onclickListener(View view, int position);
    void onLongPressClickListener(View view, int childPosition);
}
