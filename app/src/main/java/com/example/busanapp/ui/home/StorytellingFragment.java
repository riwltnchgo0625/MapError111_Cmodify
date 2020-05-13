package com.example.busanapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.busanapp.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class StorytellingFragment extends Fragment {


    public StorytellingFragment() {

    }

    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);




    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.fragment_storytelling, container, false);


        StrictMode.enableDefaults();

        TextView status1 = (TextView)view.findViewById(R.id.result);


        boolean initem = false, indataTitle = false, indataContent = false, intmpField1 = false, intmpField4 = false, infileMask = false;
        boolean inkeywordAge = false, inkeywordArea = false, inoriginal = false, insummary = false;

        String dataTitle = null, dataContent = null, tmpField1 = null, tmpField4 = null, fileMask = null;
        String keywordAge = null, keywordArea = null, original = null, summary = null;

        try{
            URL url = new URL("http://apis.data.go.kr/6260000/StorytellingInfo/getStorytellingInfo?"
                    + "pageNo=1&numOfRows=100&serviceKey="
                    + "NO2EbE%2Bu5KtWhuLp1rQALIAtWWnRDgj9mCuelgBAxRS%2Frxi12vyAMLBp%2F3KEanPiRfbO3hwggbbpZ%2B0XtKIolQ%3D%3D"
            );
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");


            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("dataTitle")){ //title 만나면 내용을 받을수 있게 하자
                            indataTitle = true;
                        }
                        if(parser.getName().equals("dataContent")){ //address 만나면 내용을 받을수 있게 하자
                            indataContent = true;
                        }
                        if(parser.getName().equals("tmpField1")){ //mapx 만나면 내용을 받을수 있게 하자
                            intmpField1 = true;
                        }
                        if(parser.getName().equals("tmpField4")){ //mapy 만나면 내용을 받을수 있게 하자
                            intmpField4 = true;
                        }
                        if(parser.getName().equals("fileMask")){ //mapy 만나면 내용을 받을수 있게 하자
                            infileMask = true;
                        }
                        if(parser.getName().equals("keywordAge")){ //mapy 만나면 내용을 받을수 있게 하자
                            inkeywordAge = true;
                        }
                        if(parser.getName().equals("keywordArea")){ //mapy 만나면 내용을 받을수 있게 하자
                            inkeywordArea = true;
                        }
                        if(parser.getName().equals("original")){ //mapy 만나면 내용을 받을수 있게 하자
                            inoriginal = true;
                        }
                        if(parser.getName().equals("summary")){ //mapy 만나면 내용을 받을수 있게 하자
                            insummary = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(indataTitle){ //isTitle이 true일 때 태그의 내용을 저장.
                            dataTitle = parser.getText();
                            indataTitle = false;
                        }
                        if(indataContent){ //isAddress이 true일 때 태그의 내용을 저장.
                            dataContent = parser.getText();
                            indataContent = false;
                        }
                        if(intmpField1){ //isMapx이 true일 때 태그의 내용을 저장.
                            tmpField1 = parser.getText();
                            intmpField1 = false;
                        }
                        if(intmpField4){ //isMapy이 true일 때 태그의 내용을 저장.
                            tmpField4 = parser.getText();
                            intmpField4 = false;
                        }
                        if(infileMask){ //isMapy이 true일 때 태그의 내용을 저장.
                            fileMask = parser.getText();
                            infileMask = false;
                        }
                        if(inkeywordAge){ //isMapy이 true일 때 태그의 내용을 저장.
                            keywordAge = parser.getText();
                            inkeywordAge = false;
                        }
                        if(inkeywordArea){ //isMapy이 true일 때 태그의 내용을 저장.
                            keywordArea = parser.getText();
                            inkeywordArea = false;
                        }
                        if(inoriginal){ //isMapy이 true일 때 태그의 내용을 저장.
                            original = parser.getText();
                            inoriginal = false;
                        }
                        if(insummary){ //isMapy이 true일 때 태그의 내용을 저장.
                            summary = parser.getText();
                            insummary = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"제목 : " + dataTitle
                                    +"\n 내용 : "+ dataContent +"\n 분류: "+ tmpField1 +"\n 키워드 : " + tmpField4 +  "\n 이미지경로 : " + fileMask + "\n 시대 : " + keywordAge
                                    +"\n 지역 : " + keywordArea + "\n 원본 : " + original + "\n 요약 : " + summary
                                    +"\n"+"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
        }
        return view;
    }
}
