package com.example.jaind.instaveritas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Devashish Jain on 06-06-2016.
 */
public class SearchAdapter extends BaseAdapter {
    ArrayList<SearchData> detail;
    private Context mContext;

    public SearchAdapter(Context c, ArrayList<SearchData> ticketdata) {

        this.mContext = c;

        this.detail = ticketdata;
    }

    public static Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
//        canvas.drawOval(rectF, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    @Override
    public int getCount() {
        return detail.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.searchlist, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.actor = (TextView) convertView.findViewById(R.id.actor);
            holder.director = (TextView) convertView.findViewById(R.id.director);
            holder.img = (ImageView) convertView.findViewById(R.id.poster);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.title.setText(detail.get(position).getShow_title());
        holder.actor.setText(detail.get(position).getShow_cast());
        holder.director.setText(detail.get(position).getDirector());


        final ViewHolder finalHolder = holder;
        holder.actor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searctText = finalHolder.actor.getText().toString();
                ArrayList<String> items = new ArrayList(Arrays.asList(searctText.split("\\s*,\\s*")));
                Appcontroller.searchShow(mContext, items.get(0), "Actor");

            }

        });

        holder.director.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searctText = finalHolder.director.getText().toString();
                ArrayList<String> items = new ArrayList(Arrays.asList(searctText.split("\\s*,\\s*")));
                Appcontroller.searchShow(mContext, items.get(0), "Director");

            }

        });


        new DownloadImageTask(holder.img).execute(detail.get(position).getPoster());
        return convertView;
    }

    public void updateAdapter(ArrayList<SearchData> newlist) {

        detail = newlist;
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView title, actor, director;
        ImageView img;

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
