package com.example.plantdoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private Spinner languageSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationSwitch = findViewById(R.id.notificationSwitch);
        languageSpinner = findViewById(R.id.languageSpinner);
        Button saveSettingsButton = findViewById(R.id.saveSettingsButton);

        saveSettingsButton.setOnClickListener(view -> saveSettings());
    }

    private void saveSettings() {
        boolean notificationsEnabled = notificationSwitch.isChecked();
        String selectedLanguage = languageSpinner.getSelectedItem().toString();

        // Show a confirmation message
        Toast.makeText(this, "Settings Saved:\n" +
                "Notifications: " + (notificationsEnabled ? "Enabled" : "Disabled") + "\n" +
                "Language: " + selectedLanguage, Toast.LENGTH_SHORT).show();
    }
}
