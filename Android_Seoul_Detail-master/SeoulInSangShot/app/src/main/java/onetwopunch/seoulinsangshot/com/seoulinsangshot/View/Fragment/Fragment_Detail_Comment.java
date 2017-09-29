package onetwopunch.seoulinsangshot.com.seoulinsangshot.View.Fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.ArrayList;

import onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller.Adapter_Comment;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Controller.Constants;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Data.CommentVO;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.DataManager.Remote.RetrofitService;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.Model.Model_Comment;
import onetwopunch.seoulinsangshot.com.seoulinsangshot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kwakgee on 2017. 9. 17..
 */

public class Fragment_Detail_Comment extends Fragment {

    TextView qnaTv;
    Button qnaBt;
    EditText qnaEv;

    //엑티비티로부터의 번들 저장 변수
    String initials;

    RecyclerView rv_comment;
    LinearLayoutManager manager;
    Adapter_Comment adapter_comment;

    public CommentVO repoList;
    public ArrayList<Model_Comment> tempList;
    public ArrayList<Model_Comment> commentList;

    public Fragment_Detail_Comment() {

    }

    public static Fragment_Detail_Comment newInstance() {
        Fragment_Detail_Comment fragment = new Fragment_Detail_Comment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_comment, container, false);

       /* qnaTv = (TextView) rootView.findViewById(R.id.QnATextView);
        qnaBt = (Button) rootView.findViewById(R.id.QnAButton);
        qnaEv = (EditText) rootView.findViewById(R.id.QnaEditText);*/


        rv_comment=(RecyclerView)rootView.findViewById(R.id.comment_RecyclerView);


        AndroidNetworking.initialize(rootView.getContext());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //엑티비티로 부터 온 번들 값 저장.
        initials = getArguments().getString("initials");

        manager = new LinearLayoutManager(rootView.getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);


        //레트로핏
        Retrofit client = new Retrofit.Builder().baseUrl(Constants.TEST_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitService service = client.create(RetrofitService.class);

        Call<CommentVO> call = service.getCommentData();
        call.enqueue(new Callback<CommentVO>() {
            @Override
            public void onResponse(Call<CommentVO> call, Response<CommentVO> response) {
                if (response.isSuccessful()) {
                    repoList = response.body();
                    tempList = repoList.getList();
                    commentList=new ArrayList<Model_Comment>();
                   for (int i = 0; i < tempList.size(); i++) {
                        if (tempList.get(i).getInit().equals(initials)) {
                            String init = tempList.get(i).getInit();
                            String id = tempList.get(i).getId();
                            String text = tempList.get(i).getText();
                            commentList.add(new Model_Comment(init, id, text));
                        }
                    }
                    adapter_comment = new Adapter_Comment(getContext(), commentList);
                    rv_comment.setLayoutManager(manager);
                    rv_comment.setAdapter(adapter_comment);
                }

            }

            @Override
            public void onFailure(Call<CommentVO> call, Throwable t) {
            }
        });













       /* //POST 하는 부분. 서버-DB 쪽이랑 최대한 변수 맞춰서 정리하자.
        qnaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text= qnaEv.getText().toString();
                String area ="YS1";
                String num="1";
                String id="joker1649";

                AndroidNetworking.post("http://13.124.87.34:3000/preply")
                        .addBodyParameter("id",id)
                        .addBodyParameter("area",area)
                        .addBodyParameter("text",text)
                        .addHeaders("Content-Type", "multipart/form-data")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                            }
                            @Override
                            public void onError(ANError anError) {
                            }
                        });
            }
        });*/

        return rootView;

    }
}
