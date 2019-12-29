package itt.dam.android.actividad01_xmas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Se oculta la barra superior
        getSupportActionBar().hide();

        // Se declara el view para la imagen de Papa Noel por la chimenea
        final ImageView splashSanta = (ImageView) findViewById(R.id.imageViewSplashSanta);

        // Se declara el recurso del svg animado
        final AnimatedVectorDrawableCompat avdSplashSanta = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_santa_splash);

        // Se le asigna al view el recurso animado.
        splashSanta.setImageDrawable(avdSplashSanta);

        // Se arranca la animacion del papa noel bajando por la ventana.
        avdSplashSanta.start();


        // Textos y animaciones de los textos del splash
        TextView txtSplashTit = (TextView) findViewById(R.id.textViewSplash);
        TextView txtSplashAuthor = (TextView) findViewById(R.id.textViewSplashCreditos);
        Animation animSplashTxt = AnimationUtils.loadAnimation(this, R.anim.anim_splash_txt_tit);
        Animation animSplashAuthor = AnimationUtils.loadAnimation(this, R.anim.anim_splash_txt_author);
        txtSplashTit.startAnimation(animSplashTxt);
        txtSplashAuthor.startAnimation(animSplashAuthor);

        // Listener sobre la animacion del titulo para pasar al MainActivity cuando termine
        // Se debe implementar la interface Animation.AnimationListener y sus metodos
        // Sobre el metodo onAnimationEnd se realiza la logica para finalizar el splash y pasar al
        // Main Activity.
        animSplashTxt.setAnimationListener(this);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Forma implementada
        startActivity(new Intent(this, MainActivity.class));

        // Se aplica la transicion entre ambas activities.
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        //  Forma Apuntes.
        //  Intent openToApp = new Intent(SplashActivity.this, MainActivity.class);
        //  startActivity(openToApp);

        // Se finaliza la ejecucion del Splash
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
