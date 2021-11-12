package id.ac.umn.dimas.week11_41281;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    static final PostServices create() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        return retrofit.create(PostServices.class);
    }
}
