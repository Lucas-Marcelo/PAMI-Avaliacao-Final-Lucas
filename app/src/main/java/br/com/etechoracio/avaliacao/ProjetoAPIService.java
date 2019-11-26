package br.com.etechoracio.avaliacao;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProjetoAPIService {
    @POST(".")
    Call<String> onMultiplicar(@Body Request request);
}
