package zeev.fraiman.mendeleevtable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import coil.Coil;
import coil.request.ImageRequest;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementViewHolder> {

    private final ArrayList<Element> elementList;
    public ElementAdapter(ArrayList<Element> elementList) {
        this.elementList = elementList;
    }

    @NonNull
    @Override
    public ElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_element,
                parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementViewHolder holder, int position) {
        Element element = elementList.get(position);
        holder.tvSymbol.setText(element.getSymbol());
        holder.tvName.setText(element.getName());
        holder.tvAtomicNumber.setText(String.valueOf(element.getAtomicNumber()));
        holder.tvAtomicMass.setText(String.valueOf(element.getAtomicMass()));
        String imageUrl = element.getImageUrl();

        /*ImageRequest request = new ImageRequest.Builder(holder.itemView.getContext())
                .data(imageUrl)
                .size(64, 64)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.bad)
                .target(holder.ivElement)
                .build();
        Coil.imageLoader(holder.itemView.getContext()).enqueue(request);*/

        new LoadImageTask(holder.ivElement).execute(element.getImageUrl());
    }


    @Override
    public int getItemCount() {
        return elementList.size();
    }

    public static class ElementViewHolder extends RecyclerView.ViewHolder {

        TextView tvSymbol, tvName, tvAtomicNumber, tvAtomicMass;
        ImageView ivElement;

        public ElementViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSymbol = itemView.findViewById(R.id.tvSymbol);
            tvName = itemView.findViewById(R.id.tvName);
            tvAtomicNumber = itemView.findViewById(R.id.tvAtomicNumber);
            tvAtomicMass = itemView.findViewById(R.id.tvAtomicMass);
            ivElement = itemView.findViewById(R.id.ivElement);
        }
    }

    private static class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        private final ImageView imageView;

        public LoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream;
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setDoInput(true);
                connection.connect();
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            } else {
                imageView.setImageResource(R.drawable.bad); // Показываем ошибочное изображение
            }
        }
    }
}
