package pl.hypeapps.blackmirror.network.api.weather;

import io.reactivex.Single;
import pl.hypeapps.blackmirror.BuildConfig;
import pl.hypeapps.blackmirror.model.weather.WeatherResponse;
import pl.hypeapps.blackmirror.network.WeatherRepository;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Klasa warstwy dostępu do danych pogodowych.
 */
public class WeatherDataSource implements WeatherRepository {

    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5/";

    private static final String API_KEY = BuildConfig.OPENWEATHERMAP_API_KEY;

    private final WeatherApi weatherApi;

    public WeatherDataSource() {
        weatherApi = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi.class);
    }

    /**
     * @param cityName miasto, kraj, wieś.
     * @param units system jednostek.
     * @param lang jezyk opisu wiadomości.
     * @return Zwraca pogodę na podstawie podanej lokalizacji.
     */
    @Override
    public Single<WeatherResponse> getWeatherByCityName(String cityName, String units, String lang) {
        return weatherApi.getWeatherByCityName(API_KEY, cityName, units, lang);
    }

}
