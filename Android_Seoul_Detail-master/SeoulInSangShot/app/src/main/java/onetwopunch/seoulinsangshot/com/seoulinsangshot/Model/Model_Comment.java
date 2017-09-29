package onetwopunch.seoulinsangshot.com.seoulinsangshot.Model;

/**
 * Created by 301 on 2017-09-28.
 */

public class Model_Comment {
    String init;    //장소이름 ex) YS-1
    String id;      //댓글단 사람 id
    String text;    //댓글

    public Model_Comment() {

    }

    public Model_Comment(String init, String id, String text) {
        this.init = init;
        this.id = id;
        this.text = text;
    }

    public String getInit() {
        return init;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
