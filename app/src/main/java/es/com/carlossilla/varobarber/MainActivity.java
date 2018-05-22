package es.com.carlossilla.varobarber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton Ibtn_nosotros,Ibtn_login,Ibtn_contacto,Ibtn_servicios,Ibtn_maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ibtn_nosotros = findViewById(R.id.imageButtonNosotros);
        Ibtn_login = findViewById(R.id.imageButtonLogin);

        Ibtn_nosotros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Nosotros.class);
                startActivity(intent);
            }
        });

        Ibtn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
            }
        });


    }
}
