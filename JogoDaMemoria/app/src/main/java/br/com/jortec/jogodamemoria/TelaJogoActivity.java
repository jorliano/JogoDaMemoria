package br.com.jortec.jogodamemoria;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

import br.com.jortec.jogodamemoria.adapter.GridAdapter;
import me.drakeet.materialdialog.MaterialDialog;

public class TelaJogoActivity extends AppCompatActivity {
    private GridView gridView;
    int[] lista;
    int[] listaAux;
    long itemSelecionado;
    int posicaoSelecionado;
    int nivel = 1;
    boolean fimJogo = true;
    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

        if(nivel == 1)
        carregarTabuleiro(12);
        if(nivel == 2)
        carregarTabuleiro(16);
        if (nivel == 3)
        carregarTabuleiro(20);

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
                          /*  for (int i = 0; i < lista.length; i++) {
                                fimJogo = true;
                                if (lista[i] == R.drawable.card) {
                                    fimJogo = false;
                                }
                            }
                            if (fimJogo) {
                                materialDialog = new MaterialDialog(view.getContext())
                                        .setTitle("MaterialDialog")
                                        .setMessage("Parabéns você filinalizou o jogo, Deseja reiniciar ?")
                                        .setPositiveButton("OK", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                lista = new int[nivel];
                                                for (int i = 0; i < nivel; i++) {
                                                    lista[i] = R.drawable.card;
                                                }
                                                gridView.invalidateViews();
                                            }
                                        })
                                        .setNegativeButton("CANCEL", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                materialDialog.dismiss();
                                            }
                                        });

                                materialDialog.show();

                            }*/
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

        int[] listaImagens = new int[]{R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5,
                R.drawable.card6, R.drawable.card7, R.drawable.card8, R.drawable.card9, R.drawable.card10};

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
