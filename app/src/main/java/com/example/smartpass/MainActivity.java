package com.example.smartpass;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private Button scanButton;
    private TextView scannedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Keeps the edge-to-edge layout intact
        setContentView(R.layout.activity_main);

        // Set up window insets (system bars like status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        scanButton = findViewById(R.id.scan_button);

        // Set up the scan button click listener
        scanButton.setOnClickListener(v -> {
            initiateScan();
        });
    }

    // Method to initiate the QR code scanner
    private void initiateScan() {
        // Create ScanOptions to configure the scanner
        ScanOptions options = new ScanOptions();
        options.setPrompt("Place the QR code inside the viewfinder");
        options.setBeepEnabled(true);  // Enable the beep sound when QR is scanned
        options.setOrientationLocked(false);  // Unlock the orientation of the scanner

        // Start the scanner activity
        barcodeLauncher.launch(options);
    }

    // Activity result launcher to get the scanned result
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(
            new ScanContract(), result -> {
                if (result.getContents() != null) {
                    // Handle the scanned result and display it
                    String scannedData = result.getContents();

                    // Optionally, show a toast message with the scanned result
                    Toast.makeText(MainActivity.this, "Scanned: " + scannedData, Toast.LENGTH_LONG).show();
                } else {
                    // Handle if no QR code was scanned
                    Toast.makeText(MainActivity.this, "Scan cancelled", Toast.LENGTH_SHORT).show();
                }
            });
}
