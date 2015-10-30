package br.com.jortec.jogodamemoria;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

import br.com.jortec.jogodamemoria.adapter.GridAdapter;
import me.drakeet.materialdialog.MaterialDialog;

public class TelaJogoActivity extends AppCompatActivity {
    private GridView gridView;
    private Toolbar toolbar;
    private Chronometer cronometro;
    int[] lista;
    int[] listaAux;
    int[] listaImagens;
    long itemSelecionado;
    int posicaoSelecionado, nivel = 1;
    long tempo;
    boolean fimJogo = true;
    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

       listaImagens = new int[]{R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5,
                R.drawable.card6, R.drawable.card7, R.drawable.card8, R.drawable.card9, R.drawable.card10};

        if(savedInstanceState != null) {
            int tamanho = 12;
            if(nivel == 2)
                tamanho = 16;
            else if (nivel == 3)
                tamanho = 20;

            lista = new int [tamanho];
            listaAux = new int[tamanho];
            for (int i = 0;i < tamanho; i++){
                lista[i] = Integer.parseInt(savedInstanceState.getString("lista"+i));
                listaAux[i] = Integer.parseInt(savedInstanceState.getString("listaAux"+i));
            }
            tempo = Long.parseLong(savedInstanceState.getString("tempo"));
        }else{
            if(nivel == 1)
                carregarTabuleiro(12);
            if(nivel == 2)
                carregarTabuleiro(16);
            if (nivel == 3)
                carregarTabuleiro(20);
        }


        toolbar = (Toolbar) findViewById(R.id.jogo_toolbar);
        toolbar.setTitle(R.string.jogo_da_memoria);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cronometro = (Chronometer) findViewById(R.id.chronometer);
        cronometro.setBase(SystemClock.elapsedRealtime() - tempo);
        cronometro.start();

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this, lista));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                // Toast.makeText(getBaseContext()," Posição : "+position+" id : "+id ,Toast.LENGTH_LONG).show();

                if (id == R.drawable.card) {
                    lista[position] = listaAux[position];
                    gridView.invalidateViews();

                    if (itemSelecionado != 0 && posicaoSelecionado != position) {


                        //Compara se são iquais caso não seja vira as cartas novamente
                        if (itemSelecionado != listaAux[position]) {
                            //Thread para demorar um tempo
                            Handler handler = new Handler();
                            long delay = 1000; // tempo de delay em millisegundos
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    // código a ser executado após o tempo de delay
                                    lista[posicaoSelecionado] = R.drawable.card;
                                    lista[position] = R.drawable.card;
                                    gridView.invalidateViews();
                                    itemSelecionado = 0;
                                    posicaoSelecionado = 0;
                                }
                            }, delay);

                        /*    new Thread(new Runnable() {
                                public void run() {
                                    SystemClock.sleep(1000);
                                }
                            }).start();*/
                        } else {
                            // Toast.makeText(getBaseContext(), "Parabéns ", Toast.LENGTH_SHORT).show();
                            itemSelecionado = 0;
                            posicaoSelecionado = 0;

                            //Ver se o jogo terminou
                            for (int i = 0; i < lista.length; i++) {
                                fimJogo = true;
                                if (lista[i] == R.drawable.card) {
                                    fimJogo = false;
                                    break;
                                }
                            }
                            if (fimJogo) {
                                cronometro.stop();
                                materialDialog = new MaterialDialog(view.getContext())
                                        .setTitle("MaterialDialog")
                                        .setMessage("Parabéns você filinalizou o jogo em "+cronometro.getBase()+", Deseja reiniciar ?")
                                        .setPositiveButton("SIM", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                reiniciar();
                                                materialDialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton("NÂO", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                materialDialog.dismiss();
                                            }
                                        });

                                materialDialog.show();

                            }
                        }
                    } else {
                        itemSelecionado = listaAux[position];
                        posicaoSelecionado = position;
                    }


                } else {

                }


            }
        });

    }

    public void carregarTabuleiro(int nivel) {
        lista = new int[nivel];
        for (int i = 0; i < nivel; i++) {
            lista[i] = R.drawable.card;
        }

        listaAux = new int[nivel];
        for (int i = 0; i < nivel; i ++){
            if(i >= nivel/2  ){
                listaAux[i] = listaImagens[i - nivel/2];
            }else{
                listaAux[i] = listaImagens[i];
            }
        }

        embaralhar(listaAux);
    }

    //método estático que embaralha
    public void embaralhar(int[] v) {
        Random random = new Random();
        for (int i = 0; i < (v.length - 1); i++) {
            //sorteia um índice
            int j = random.nextInt(v.length);
            //troca o conteúdo dos índices i e j do vetor
            int temp = v[i];
            v[i] = v[j];
            v[j] = temp;
        }
    }
    //Reiniciar o jogo
    public void  reiniciar(){

        for (int i = 0; i < lista.length; i++) {
            lista[i] = R.drawable.card;
        }
        gridView.invalidateViews();
        embaralhar(listaAux);
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_jogo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == android.R.id.home){
            finish();
        }
        if (id == R.id.action_reiniciar) {
            reiniciar();
        }

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(lista != null && listaAux != null) {
            for (int i = 0;i < lista.length; i++){
                outState.putString("lista"+i, String.valueOf(lista[i]));
                outState.putString("listaAux"+i, String.valueOf(listaAux[i]));
            }

            outState.putString("tempo", String.valueOf(SystemClock.elapsedRealtime() - cronometro.getBase()));
        }
    }
}
