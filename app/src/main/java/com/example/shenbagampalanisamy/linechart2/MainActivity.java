package com.example.shenbagampalanisamy.linechart2;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.example.shenbagampalanisamy.linechart2.R.drawable.ic_tag_faces;

public class MainActivity extends AppCompatActivity {
    DrawView  drawLine;
    int h,w,x,y;
    String title,xtitle,ytitle;
    ArrayList Xaxis=new ArrayList();
    ArrayList Yaxis=new ArrayList();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        try {
            InputStream is = getAssets().open("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            Element element = doc.getDocumentElement();
            element.normalize();
            NodeList nList = doc.getElementsByTagName("chart");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                   // w = Integer.parseInt(getValue("width", element2));
                  //  h = Integer.parseInt(getValue("hight", element2));
                    x = Integer.parseInt(getValue("xaxies", element2));
                    y = Integer.parseInt(getValue("yaxies", element2));
                }
            }
                /* nList = doc.getElementsByTagName("captions");
                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        title = getValue("Title", element2);
                        xtitle = getValue("xtitle", element2);
                        ytitle = getValue("ytitle", element2);
                    }
                }*/

                nList = doc.getElementsByTagName("plot");
                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element2 = (Element) node;
                        Xaxis.add(getValue("xaxis_point", element2));
                        Yaxis.add(getValue("yaxis_points", element2));
                    }
                }

           Log.i("val", String.valueOf(Xaxis));
        }catch (Exception e) {e.printStackTrace();}
        BeanClass b = new BeanClass(w,h,x,y,Xaxis,Yaxis);
        // Log.i("txt", String.valueOf(Xaxis.get(0)));

//        DrawView d=new DrawView(this);

/*
        RelativeLayout rel=(RelativeLayout)findViewById(R.id.relt);
        int w=b.getWidth();
        int h=b.getHeight();
        int left=b.getXaxies();
        int right=b.getYaxies();
        RelativeLayout.LayoutParams rl=new RelativeLayout.LayoutParams(w,h);
        rl.setMargins(left,right,0,0);
        d.setLayoutParams(rl);
        d.setBackgroundColor(Color.GRAY);
        Drawable d1=d.getBackground();
        d1.setAlpha(51);
*/



        // public Panel(Context context, AttributeSet attrs) {
         //   super(context, attrs);


  //      rel.addView(d);

        DrawView dr=(DrawView) findViewById(R.id.id1);
        dr.setValue(b);

        //final int num = d.getBackground().getOpacity();
        // setContentView(d);
    }
    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}