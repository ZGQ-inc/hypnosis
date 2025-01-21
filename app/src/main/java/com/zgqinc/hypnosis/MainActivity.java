package com.zgqinc.hypnosis;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import coil.ImageLoader;
import coil.decode.GifDecoder;
import coil.request.ImageRequest;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private int currentImageIndex = 0;
    private final int[] gifResources = {
            R.drawable.giphy,
            R.drawable.giphy2,
            R.drawable.giphy3,
            R.drawable.giphy4,
            R.drawable.giphy5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hypnosis_img);
        imageView = findViewById(R.id.imageView);

        ImageLoader imageLoader = new ImageLoader.Builder(this)
                .components(new coil.ComponentRegistry.Builder().add(new GifDecoder.Factory()).build())
                .build();

        loadGif(imageLoader, gifResources[currentImageIndex]);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % gifResources.length;
                loadGif(imageLoader, gifResources[currentImageIndex]);
            }
        });
    }

    private void loadGif(ImageLoader imageLoader, int resourceId) {
        ImageRequest request = new ImageRequest.Builder(this)
                .data(resourceId)
                .target(imageView)
                .build();
        imageLoader.enqueue(request);
    }
}