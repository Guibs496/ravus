package clement.guibert.ravus;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    //Créer le lien entre les différentes activités
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Quand on appuie sur le bouton "Se connecter avec Twitter" cela lance la classe Authentification
    public void onClick(View v) {
        Intent intent = new Intent(this, Authentification.class);
        startActivity(intent);
    }

}