package id.ac.umn.dimas.week11_41281;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostServices {
    @GET("posts")
    Call<List<PostModel>> getPosts();
}
