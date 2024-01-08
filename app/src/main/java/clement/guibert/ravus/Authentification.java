package clement.guibert.ravus;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class Authentification extends AppCompatActivity {

    private Twitter twitter;
    private RequestToken requestToken;
    private String twitter_consumer_key = "buildConfig.TWITTER_CONSUMER_KEY";
    private String twitter_consumer_secret = "buildConfig.TWITTER_CONSUMER_SECRET";
    private String callback_url = "";
    private String oauth_verifier = "UDc5b1B6WnR4dnQxdHZyeTkxSkQ6MTpjaQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Configuration de Twitter4J
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(twitter_consumer_key, twitter_consumer_secret);

        // Appeler getOAuthRequestToken dans un AsyncTask
        new OAuthRequestTokenTask().execute();
    }

    private class OAuthRequestTokenTask extends AsyncTask<Void, Void, RequestToken> {

        @Override
        protected RequestToken doInBackground(Void... voids) {
            try {
                return twitter.getOAuthRequestToken(callback_url);
            } catch (TwitterException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(RequestToken result) {
            if (result != null) {
                // Continuer votre logique ici après avoir obtenu le jeton de requête
                requestToken = result;
                // Redirigez l'utilisateur vers l'URL d'authentification Twitter
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(requestToken.getAuthenticationURL()));
                startActivity(intent);
            } else {
                Toast.makeText(Authentification.this, "Erreur lors de l'obtention du jeton de requête", Toast.LENGTH_SHORT).show();
                finish(); // Terminer l'activité en cas d'erreur
            }
        }
    }

}




