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
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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
        if(bean!=null) {
            w = bean.getWidth()-100;
            Log.i("width11",Integer.toString(w));
            h =bean.getHeight()-200;
            Log.i("height11",Integer.toString(h));
            ArrayList Xaxis=new ArrayList(bean.getXaxis());
            ArrayList Yaxis=new ArrayList(bean.getYaxis());
          // int xaxis[] = new int[]{100,440,510,600,200};
           //int yaxis[] = new int[]{20,10,30,50,100};
            Map<String, Integer> map = new HashMap<String, Integer>();
            Map<String, Integer> map1 = new HashMap<String, Integer>();
           int re=checkFormate(Xaxis,Yaxis);
            Log.i("string",String.valueOf(re));

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
               canvas.drawLine(r_left, r_top, r_left, r_bottom, paint);
               canvas.drawLine(r_left, r_bottom, r_right + 100, r_bottom, paint);

            if(re==1)
            {
                integerValue(w,h,top,r_left,r_right,r_bottom,r_top,Xaxis,Yaxis,canvas,map,map1);
            }
            else
            {
                stringValue(w,h,top,r_left,r_right,r_bottom,r_top,Xaxis,Yaxis,canvas,map,map1);
            }
        }else{
            return;
        }
    }

    private void stringValue(int w, int h, int top, int r_left, int r_right, int r_bottom, int r_top, ArrayList xaxis, ArrayList yaxis, Canvas canvas1, Map<String, Integer> map, Map<String, Integer> map1) {

        int m=(w-200)/xaxis.size();
        int n=(h-200)/yaxis.size();
        int x = r_left + m;
        int y = r_bottom + 50;
        int x1 = r_left- 50;
        int y1 = r_bottom - n;
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        for (int i = 0; i < xaxis.size(); i++) {
            canvas1.drawText((String) xaxis.get(i), x, y, paint);
            canvas1.drawLine(x, r_top, x, r_bottom, paint);
            map.put(xaxis.get(i).toString(), x);

            x = x + m;
        }
        paint.setColor(Color.parseColor("red"));
        for (int i = 0; i < yaxis.size(); i++) {
            canvas1.drawText((String)yaxis.get(i), x1, y1, paint);
            canvas1.drawLine(r_top, y1, r_right, y1, paint);
            map1.put(yaxis.get(i).toString(), y1);
            y1 = y1 - n;
        }
        textPath(top,r_bottom,h,canvas1,paint);
        int x_i[] = new int[map.size()];
        int y_i[] = new int[map1.size()];
        int num = 0;
        int num1 = 0;

        for (int p = 0; p < xaxis.size(); p++) {

            int a = (int)map.get(xaxis.get(p));
            x_i[num] = a;
            num++;





            int b = (int)map1.get(yaxis.get(p));
            y_i[num1] = b;
            num1++;
            canvas1.drawCircle(a, b, 5, paint);
            Paint p2=new Paint();
            p2.setColor(Color.BLACK);
            canvas1.drawCircle(203.2f,594.5f,6.0f,p2);
        }
        Paint p1=new Paint();
        p1.setColor(Color.BLACK);
        for (int w11 = 0; w11 <xaxis.size() - 1; w11++) {
            canvas1.drawLine(x_i[w11], y_i[w11], x_i[w11 + 1], y_i[w11 + 1], p1);

        }

    }

    public int checkFormate(ArrayList xaxis1, ArrayList yaxis1) {
        String a=(String)xaxis1.get(0);
        String b=(String)yaxis1.get(0);
        int len1=a.length(),len2=b.length();
        Log.i("jfiejf",String.valueOf(6));
        int count=0;
        int count1=0;
        int flag=0;
        int flag1=0;
        for(int i=0;i<len1;i++)
        {
            if(Character.isDigit(a.charAt(i)))
            {
                count++;
            }
        }

        for(int i=0;i<len2;i++)
        {
            if(Character.isDigit(b.charAt(i)))
            {
                count1++;
            }
        }
        Log.i("count1",String.valueOf(count1));

        if(count==len1)
        {
            flag=1;
        }
        Log.i("flag",String.valueOf(flag));
        if(count1==len2)
        {
            flag1=1;
        }
        Log.i("flag",String.valueOf(flag1));
        if(flag==1&&flag1==1)
        {
            return 1;

        }
        else
        {
            return 0;
        }

    }

    private void integerValue(int w1, int h1, int top, int r_left1, int r_right1, int r_bottom1, int r_top1, ArrayList xaxis1, ArrayList yaxis1, Canvas canvas, Map<String, Integer> map, Map<String, Integer> map1) {

        int xsize=xaxis1.size();
        int xaxis[]=new int[xsize];
        for(int i=0;i<xsize;i++)
        {
            xaxis[i]= Integer.parseInt((String) xaxis1.get(i)) ;
        }
        int ysize=xaxis1.size();
        int yaxis[]=new int[ysize];
        for(int i=0;i<ysize;i++)
        {
            yaxis[i]= Integer.parseInt((String) yaxis1.get(i)) ;
        }
        Integer[] xa = new Integer[xaxis.length];
        Integer[] xb = new Integer[yaxis.length];
        for (int i = 0; i < xaxis.length; i++) {
            xa[i] = xaxis[i];
            xb[i] = yaxis[i];
        }
        ArrayList xscale = new ArrayList(Arrays.asList(xa));
        List l2 = new ArrayList(Arrays.asList(xb));
        List xscale1 = new ArrayList<Integer>();
        List<Integer> yscale1 = new ArrayList<Integer>();

        int max = (int) Collections.max(xscale);
        int min = (int) Collections.min(xscale);
        int value = (max - min) / xscale.size();
        int temp2 = min;
        int count = 0;
        for (; ; ) {
            count++;
            if (temp2 <= max) {
                xscale1.add(temp2);
                temp2 = temp2 + value;

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
        int n = (h1 - 200) / yscale1.size();
        int m = (w1 - 200) / xscale1.size();
        int x = r_left1 + m;
        int y = r_bottom1 + 50;
        int x1 = r_left1 - 50;
        int y1 = r_bottom1 - n;
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        int pix =(int) m / value;
        for (int i = 0; i < xscale1.size(); i++) {
            canvas.drawText(Integer.toString((Integer) xscale1.get(i)), (float) x, y, paint);
            canvas.drawLine(x, r_top1, x, r_bottom1, paint);
            map.put(xscale1.get(i).toString(), x);
            int count3 = 0;
            int xw = x + pix;
            int va = (int) xscale1.get(i);
            for (int q = 0; q < value; q++) {
                va++;
                map.put(String.valueOf(va), xw + (pix * (q + 1)));
                count3++;
            }
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
            canvas.drawLine(r_top1, y1, r_right1, y1, paint);
            map1.put(yscale1.get(i).toString(), y1);
            int va1 = yscale1.get(i);
            for (int q = 0; q < value1; q++) {
                va1++;
                map1.put(String.valueOf(va1), xw1 - (pix1 * (q+1)));
            }
            y1 = y1 - n;
        }
        textPath(top,r_bottom1,h1,canvas,paint);
        int x_i[] = new int[map.size()];
        int y_i[] = new int[map1.size()];
        int num = 0;
        int num1 = 0;

        for (int p = 0; p < xscale.size(); p++) {

            int a = (int)map.get(Integer.toString((int)xscale.get(p)));
            x_i[num] = a;
            num++;
            int b = (int)map1.get(Integer.toString((int)l2.get(p)));
            y_i[num1] = b;
            num1++;
            canvas.drawCircle(a, b, 5, paint);
        }
        paint1.setColor(Color.BLACK);
        for (int w11 = 0; w11 <xscale.size() - 1; w11++) {
            canvas.drawLine(x_i[w11], y_i[w11], x_i[w11 + 1], y_i[w11 + 1], paint1);
        }

    }

    private void textPath(int top, int r_bottom, int h, Canvas canvas2, Paint paint2) {
        Path path = new Path();
        paint2.setColor(Color.TRANSPARENT);
        path.moveTo(top - 100, r_bottom);
        path.lineTo(top - 100, r_bottom - 200);
        path.lineTo(top - 100, r_bottom - 300);
        canvas2.drawPath(path, paint2);
        paint2.setStrokeWidth(0);
        paint2.setTextSize(30);
        paint2.setColor(Color.MAGENTA);
        canvas2.drawTextOnPath("Y_axies_values", path, 0, 0, paint2);
        canvas2.drawTextOnPath("Y_axies_values", path, 0, 0, paint2);
        canvas2.drawText("X_axies_values", top + 100, h + 200, paint2);
    }
    private void plot(Map map, Map map1, ArrayList xaxis, ArrayList yaxis, Canvas canvas1) {


    }


    public void drawline1(Canvas canvas, int r_left, int r_top, int r_right, int r_bottom)
    {
        paint.setColor(Color.BLACK);
        canvas.drawRect(r_left, r_top, r_right, r_bottom, paint);
    }
    
}

