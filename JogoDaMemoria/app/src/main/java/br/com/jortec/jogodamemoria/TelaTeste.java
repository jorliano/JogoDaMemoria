package br.com.jortec.jogodamemoria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import br.com.jortec.jogodamemoria.adapter.RecycleAdapter;

public class TelaTeste extends AppCompatActivity {
   // private Toolbar toolbar;
    private RecyclerView recyclerView;
    private int tamhoGrid = 4;
    private int[] lista ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_teste);

        lista = new int[]{R.drawable.cardjogo1, R.drawable.cardjogo2, R.drawable.cardjogo3, R.drawable.cardjogo4, R.drawable.cardjogo5,
                R.drawable.cardjogo6, R.drawable.cardjogo7, R.drawable.cardjogo8, R.drawable.cardjogo9, R.drawable.cardjogo10};

        if(savedInstanceState != null){
            tamhoGrid = 6;
        }


        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,tamhoGrid,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

       /* StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(llm);*/



        RecycleAdapter adapter = new RecycleAdapter(this,lista);
         recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_teste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
