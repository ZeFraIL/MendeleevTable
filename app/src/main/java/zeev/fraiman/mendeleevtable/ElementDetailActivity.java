package zeev.fraiman.mendeleevtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import coil.Coil;
import coil.request.ImageRequest;

public class ElementDetailActivity extends AppCompatActivity {

    Context context;

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_detail);

        context=this;
        Element element = getIntent().getParcelableExtra("element");

        if (element != null) {
            TextView tvName = findViewById(R.id.tvName);
            TextView tvSymbol = findViewById(R.id.tvSymbol);
            TextView tvAtomicNumber = findViewById(R.id.tvAtomicNumber);
            TextView tvAtomicMass = findViewById(R.id.tvAtomicMass);
            ImageView ivElement = findViewById(R.id.ivElement);

            tvName.setText(element.getName());
            tvSymbol.setText(element.getSymbol());
            tvAtomicNumber.setText("Atomic Number: " + element.getAtomicNumber());
            tvAtomicMass.setText("Atomic Mass: " + element.getImageUrl());

            /*String imageUrl = element.getImageUrl();
            ImageRequest request = new ImageRequest.Builder(context)
                    .data(imageUrl)
                    .size(64, 64)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.bad)
                    .target(ivElement)
                    .build();
            Coil.imageLoader(context).enqueue(request);*/
            loadImageWithExecutor(element.getImageUrl(), ivElement);
        } else {
            // Показ сообщения и завершение активности, если данных нет
            Toast.makeText(this, "No element data found", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * Метод для загрузки изображения через ExecutorService
     *
     * @param imageUrl   URL изображения
     * @param imageView  ImageView для отображения изображения
     */
    private void loadImageWithExecutor(final String imageUrl, final ImageView imageView) {
        executorService.execute(() -> {
            try {
                // Загрузка изображения из URL
                InputStream inputStream = new java.net.URL(imageUrl).openStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Обновление ImageView в основном потоке
                imageView.post(() -> {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        // Установка placeholder при ошибке
                        imageView.setImageResource(R.drawable.placeholder);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                // Установка placeholder при ошибке
                imageView.post(() -> imageView.setImageResource(R.drawable.placeholder));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Завершение работы ExecutorService при уничтожении активности
        executorService.shutdown();
    }
}
