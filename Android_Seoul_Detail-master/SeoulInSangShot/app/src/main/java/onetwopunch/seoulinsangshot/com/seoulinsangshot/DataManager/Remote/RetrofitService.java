package onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Remote;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.CommentVO;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.FrameVO;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.Weather1VO;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.Weather2VO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kwakgee on 2017. 9. 23..
 */

public interface RetrofitService {

    @GET("/image")
    Call<FrameVO> getTestData();

    @GET("/reply")
    Call<CommentVO> getCommentData();

    @GET("service/SecndSrtpdFrcstInfoService2/ForecastGrib")
    Call<Weather1VO> loadWeather1(@Query("ServiceKey") String serviceKey, @Query("nx") String nx, @Query("ny") String ny, @Query("base_date") String base_date, @Query("base_time") String base_time, @Query("_type") String type);

        @GET("service/SecndSrtpdFrcstInfoService2/ForecastSpaceData")
    Call<Weather2VO> loadWeather2(@Query("ServiceKey") String serviceKey, @Query("nx") String nx, @Query("ny") String ny, @Query("base_date") String base_date, @Query("base_time") String base_time, @Query("numOfRows")String numOfRows, @Query("_type") String type);

}
