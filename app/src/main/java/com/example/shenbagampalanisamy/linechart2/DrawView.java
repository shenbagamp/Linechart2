package com.example.shenbagampalanisamy.linechart2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
public class DrawView extends View {
    BeanClass bean;
    Paint paint = new Paint();
    Paint paint1=new Paint();
    Canvas canvas=new Canvas();
    String[] str;
    String[] num;
    public DrawView(Context context) {
        super(context);
    }
    public void setValue(BeanClass b) {
        bean=b;
        postInvalidate();
    }
    @Override
    public void onDraw(Canvas canvas) {
        int w=0;
        int h=0;
        // Log.i("width11",Integer.toString(w));
        if(bean!=null) {
            w = bean.getWidth()-100;
            Log.i("width11",Integer.toString(w));
            h =bean.getHeight()-200;
            Log.i("height11",Integer.toString(h));
            ArrayList Xaxis=new ArrayList(bean.getXaxis());
            ArrayList Yaxis=new ArrayList(bean.getYaxis());


           /* int xsize=Xaxis.size();
            int xaxis[]=new int[xsize];
            for(int i=0;i<xsize;i++)
            {
                xaxis[i]= Integer.parseInt((String) Xaxis.get(i)) ;
            }
            int ysize=Yaxis.size();
            int yaxis[]=new int[ysize];
            for(int i=0;i<xsize;i++)
            {
                xaxis[i]= Integer.parseInt((String) Yaxis.get(i)) ;
            }*/
           int xaxis[] = new int[]{100,440,510,600,200};
           int yaxis[] = new int[]{20,10,30,50,100};
           if(!(Character.isDigit(xaxis[0]))) {
               int top = 200;
               int left = 200;
               int r_left = left;
               int r_right = w;
               int r_top = top;
               int r_bottom = h;
               paint.setColor(Color.BLACK);
               paint.setStrokeWidth(3);
               paint.setStyle(Paint.Style.STROKE);
               paint.setStrokeWidth(0);
               drawline1(canvas, r_left, r_top, r_right, r_bottom);
               paint.setStrokeWidth(7);
               paint.setColor(Color.BLACK);
               Map<String, Integer> map = new HashMap<String, Integer>();
               Map<String, Integer> map1 = new HashMap<String, Integer>();
               canvas.drawLine(r_left, r_top, r_left, r_bottom, paint);
               canvas.drawLine(r_left, r_bottom, r_right + 100, r_bottom, paint);
               Integer[] xa = new Integer[xaxis.length];
               Integer[] xb = new Integer[xaxis.length];
               for (int i = 0; i < xaxis.length; i++) {
                   xa[i] = xaxis[i];
                   xb[i] = yaxis[i];
               }

               ArrayList xscale = new ArrayList(Arrays.asList(xa));
               List l2 = new ArrayList(Arrays.asList(xb));
               List xscale1 = new ArrayList<Integer>();
               List<Integer> yscale1 = new ArrayList<Integer>();
             //  scaleSplit(xscale, l2, xscale1, yscale1, canvas);
               int max = (int) Collections.max(xscale);
               Log.i("max", Integer.toString(max));
               int min = (int) Collections.min(xscale);
               int value = (max - min) / xscale.size();
               Log.i("size", String.valueOf(xscale.size()));
               int temp2 = min;
               int count = 0;
               for (; ; ) {
                   count++;
                   if (temp2 <= max) {
                       xscale1.add(temp2);
                       temp2 = temp2 + value;
                       Log.i("value", Integer.toString(value));
                   } else {
                       xscale1.add(temp2);
                       break;
                   }
               }
               int max1 = (int) Collections.max(l2);
               int min1 = (int) Collections.min(l2);
               int value1 = (max1 - min1) / l2.size();
               int emp = min1;
               int count1 = 0;
               for (; ; ) {
                   count1++;
                   if (emp <= max1) {
                       yscale1.add((int) emp);
                       emp = emp + value1;
                   } else {
                       yscale1.add((int) emp);
                       break;
                   }
                   count1++;
               }
               Log.i("scale", String.valueOf(yscale1));
               Log.i("sca", String.valueOf(count1));
               int n = (h - 200) / yscale1.size();
               int m = (w - 200) / xscale1.size();

               Log.i("shenba", Integer.toString(n));
               int x = r_left + m;
               Log.i("value", Double.toString(x));
               int y = r_bottom + 50;
               Log.i("value", Integer.toString(y));
               int x1 = r_left - 50;
               Log.i("value", Integer.toString(x1));
               int y1 = r_bottom - n;
               Log.i("value", Integer.toString(y1));
               paint.setStrokeWidth(2);
               paint.setColor(Color.RED);
               paint.setTextSize(20);
               paint.setAntiAlias(true);

               int pix =(int) m / value;
               Log.i("value", String.valueOf(value));
               Log.i("pix", String.valueOf(pix));
               Log.i("m", String.valueOf(m));
               for (int i = 0; i < xscale1.size(); i++) {
                   canvas.drawText(Integer.toString((Integer) xscale1.get(i)), (float) x, y, paint);
                   canvas.drawLine(x, r_top, x, r_bottom, paint);
                   map.put(xscale1.get(i).toString(), x);
                   int count3 = 0;
                   int xw = x + pix;
                   int va = (int) xscale1.get(i);
                   for (int q = 0; q < value; q++) {
                       va++;
                       map.put(String.valueOf(va), xw + (pix * (q + 1)));
                       count3++;
                   }
                   Log.i("count", String.valueOf(count3));
                   Log.i("m", Integer.toString(value));
                   Log.i("x", Integer.toString(x));
                   x = x + m;
               }
               int pix1 =(int) n / value1;
               Log.i("pix1",String.valueOf(pix1));
               Log.i("value1",String.valueOf(value1));
               Log.i("n",String.valueOf(n));
               paint.setColor(Color.parseColor("red"));
               int xw1 = y1 - pix1;
               Log.i("pix1", String.valueOf(pix1));
               Log.i("xw1", String.valueOf(xw1));
               for (int i = 0; i < yscale1.size(); i++) {
                   canvas.drawText(Integer.toString(yscale1.get(i)), x1, y1, paint);
                   canvas.drawLine(r_top, y1, r_right, y1, paint);
                   map1.put(yscale1.get(i).toString(), y1);
                   int va1 = yscale1.get(i);
                   for (int q = 0; q < value1; q++) {
                       va1++;
                       map1.put(String.valueOf(va1), xw1 - (pix1 * (q+1)));
                   }
                   y1 = y1 - n;
               }

               Path path = new Path();
               paint1.setColor(Color.TRANSPARENT);
               path.moveTo(top - 100, r_bottom);
               path.lineTo(top - 100, r_bottom - 200);
               path.lineTo(top - 100, r_bottom - 300);
               canvas.drawPath(path, paint1);
               paint.setStrokeWidth(0);
               paint.setTextSize(30);
               paint.setColor(Color.MAGENTA);
               canvas.drawTextOnPath("Y_axies_values", path, 0, 0, paint);
               canvas.drawTextOnPath("Y_axies_values", path, 0, 0, paint);
               canvas.drawText("X_axies_values", top + 100, h + 200, paint);
               int temp11[] = new int[xaxis.length];
               int temp12[] = new int[yaxis.length];
               int j = 0;
               int i = 0;
               int k = 0;
               int l = 0;
               int aaaa = map.get(Integer.toString(xaxis[0]));
               Log.i("fgdfdfdd", String.valueOf(aaaa));
               List list = new LinkedList();
               List list1 = new LinkedList();
               paint.setColor(Color.BLUE);
               int x_i[] = new int[map.size()];
               int y_i[] = new int[map1.size()];
               int num = 0;
               int num1 = 0;
               for (Map.Entry<String, Integer> entry : map1.entrySet())
                   System.out.println("Key = " + entry.getKey() +
                           ", Value = " + entry.getValue());

               for (int p = 0; p < temp11.length; p++) {
                   int a = map.get(Integer.toString(xaxis[p]));
                   // list.add(a);
                   x_i[num] = a;
                   num++;
                   int b = map1.get(Integer.toString(yaxis[p]));
                   // list.add(b);
                   y_i[num1] = b;
                   // Log.i("fgdfdfdd", String.valueOf(a));
                   num1++;
                   canvas.drawCircle(a, b, 5, paint);
               }
               paint1.setColor(Color.BLACK);
               for (int w1 = 0; w1 < temp11.length - 1; w1++) {
                   canvas.drawLine(x_i[w1], y_i[w1], x_i[w1 + 1], y_i[w1 + 1], paint1);
               }
           }
           
        }else{
            return;
        }
    }
    public void drawline1(Canvas canvas, int r_left, int r_top, int r_right, int r_bottom)
    {
        paint.setColor(Color.BLACK);
        canvas.drawRect(r_left, r_top, r_right, r_bottom, paint);
    }
}

