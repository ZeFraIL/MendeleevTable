package zeev.fraiman.mendeleevtable;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ElementAdapter adapter;
    private ArrayList<Element> elementList;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        elementList = new ArrayList<>();
        adapter = new ElementAdapter(elementList);
        recyclerView.setAdapter(adapter);

        loadElementsFromJSON();
    }

    private void loadElementsFromJSON() {
        executorService.execute(() -> {
            try {
                URL url = new URL("https://raw.githubusercontent.com/Bowserinator/Periodic-Table-JSON/master/PeriodicTableJSON.json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                if (connection.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder jsonBuilder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        jsonBuilder.append(line);
                    }

                    parseJSON(jsonBuilder.toString());
                } else {
                    showError("Ошибка соединения: " + connection.getResponseCode());
                }
            } catch (Exception e) {
                e.printStackTrace();
                showError("Ошибка загрузки данных: " + e.getMessage());
            }
        });
    }

    private void parseJSON(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray elementsArray = jsonObject.getJSONArray("elements");

            for (int i = 0; i < elementsArray.length(); i++) {
                JSONObject elementObj = elementsArray.getJSONObject(i);

                String symbol = elementObj.getString("symbol");
                String name = elementObj.getString("name");
                int atomicNumber = elementObj.getInt("number");
                double atomicMass = elementObj.optDouble("atomic_mass", 0.0);
                JSONObject img=elementObj.getJSONObject("image");
                String imageUrl = img.getString("url");

                Element element = new Element(symbol, name, atomicNumber, atomicMass, imageUrl);
                elementList.add(element);
            }

            runOnUiThread(() -> adapter.notifyDataSetChanged());
        } catch (JSONException e) {
            e.printStackTrace();
            showError("Ошибка обработки данных: " + e.getMessage());
        }
    }

    private void showError(@NonNull String message) {
        runOnUiThread(() -> Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
