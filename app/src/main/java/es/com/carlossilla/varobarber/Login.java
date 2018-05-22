package es.com.carlossilla.varobarber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private Button btn_login,btn_register;
    private EditText et1,et2;
    private RequestQueue requestQueue;
    private static final String URL = "http://www.carlossilla.com.es/proyecto/android/login.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setBackgroundDrawableResource(R.drawable.backgroundalargado);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        btn_login = findViewById(R.id.btnlogin);
        btn_register = findViewById(R.id.btnregister);
        et1 = findViewById(R.id.userEmail);
        et2 = findViewById(R.id.userPassword);

        requestQueue = Volley.newRequestQueue(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.names().get(0).equals("success")){
                                    Toast.makeText(getApplicationContext(),"Bienvenido "+jsonObject.getString("success"),Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),Logueado.class));
                                }else {
                                    Toast.makeText(getApplicationContext(),"Error "+jsonObject.getString("error"),Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("email", et1.getText().toString());
                            hashMap.put("contra", et2.getText().toString());

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Registro.class));
            }
        });

    }
}
