package br.com.jortec.jogodamemoria;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Jorliano on 31/10/2015.
 */
public class PreferenciaActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}

