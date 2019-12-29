package itt.dam.android.actividad01_xmas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Al tener la animacion de la nieve, Lo declaro en la clase para porque la musica se cortaba reproduciendola en API 26 superior y
    // y en foros encontre esta solucion para que sigua la musica.
    private MediaPlayer xmasSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView face = findViewById(R.id.imageViewFace);
        ImageView gift01vd = findViewById(R.id.imageViewGiftPink);
        ImageView gift02vd = findViewById(R.id.imageViewGiftPurple);
        ImageView gift03vd = findViewById(R.id.imageViewGiftGreen);

        TextView txtTit = findViewById(R.id.textViewTit);

        Animation animFace = AnimationUtils.loadAnimation(this, R.anim.anim_face);
        Animation animTit = AnimationUtils.loadAnimation(this, R.anim.anim_main_txt_tit);

        xmasSound = MediaPlayer.create(this, R.raw.xmashiphop);

        /* A partir de API 26.
        // Manera en al que se anima un AnimateVectorDrawable
        final AnimatedVectorDrawable avd = (AnimatedVectorDrawable) gifvd.getDrawable();
        avd.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                avd.start();
            }
        });
         */

        // Compatiblidad con API 23 en adelante.
        final AnimatedVectorDrawableCompat avd01 = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_gift01);
        final AnimatedVectorDrawableCompat avd02 = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_gift02);
        final AnimatedVectorDrawableCompat avd03 = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_gift03);

        xmasSound.setLooping(true);
        xmasSound.start();
        face.startAnimation(animFace);
        gifDance(gift01vd, avd01);
        gifDance(gift02vd, avd02);
        gifDance(gift03vd, avd03);
        txtTit.startAnimation(animTit);
    }

    // Creo un metodo para arracar la animacion de los tres regalos.
    private void gifDance(final ImageView gifvd, final AnimatedVectorDrawableCompat avd) {
        gifvd.setImageDrawable(avd);

        if (avd != null) {
            avd.registerAnimationCallback(new Animatable2Compat.AnimationCallback(){
                @Override
                public void onAnimationEnd(Drawable drawable) {
                    gifvd.post(new Runnable() {
                        public void run() {
                            avd.start();
                        }
                    });
                }
            });
        }
        avd.start();
    }
}
