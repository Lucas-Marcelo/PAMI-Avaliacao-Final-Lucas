package br.com.etechoracio.avaliacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText n1;
    private EditText n2;

    private ProjetoAPIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.editNum1);
        n2 = findViewById(R.id.editNum2);
    }

    public void onCalcular(View v) {
        ProjetoAPIService service = new Retrofit.Builder().baseUrl("http://172.16.58.22:8001/api/funcoes/multiplicacao/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ProjetoAPIService.class);


        Request request = new Request();
        request.num1 = n1.getText().toString();
        request.num2 = n2.getText().toString();



        Call<String> call = service.onMultiplicar(request);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String resultado = response.body();

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ProjetoAPIService ", "Erro:" + t.getMessage());
            }
        });
    }
}
