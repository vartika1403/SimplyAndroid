package entertainment.networkcall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import entertainment.simplyandroid.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkActivity extends AppCompatActivity {
    private static final String TAG = "NetworkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Button fetchData = findViewById(R.id.button);

        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating a retrofit object
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();

                //creating the api interface
                Api api = retrofit.create(Api.class);

                //now making the call object
                //Here we are using the api method that we created inside the api interface
                Call<List<Hero>> call = api.getHeroes();

                //then finallly we are making the call using enqueue()
                //it takes callback interface as an argument
                //and callback is having two methods onRespnose() and onFailure
                //if the request is successfull we will get the correct response and onResponse will be executed
                //if there is some error we will get inside the onFailure() method
                call.enqueue(new Callback<List<Hero>>() {
                    @Override
                    public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                        //In this point we got our hero list
                        //thats damn easy right ;)
                        Log.d(TAG, "onResponse: " + response.body());
                        List<Hero> heroList = response.body();

                        //now we can do whatever we want with this list

                    }

                    @Override
                    public void onFailure(Call<List<Hero>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}