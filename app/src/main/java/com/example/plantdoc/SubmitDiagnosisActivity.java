package com.example.plantdoc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class SubmitDiagnosisActivity extends AppCompatActivity {

    private Uri selectedImageUri;
    private EditText diagnosisTextInput;
    private TextView submissionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_diagnosis);

        diagnosisTextInput = findViewById(R.id.diagnosisTextInput);
        submissionStatus = findViewById(R.id.submissionStatus);
        Button selectImageButton = findViewById(R.id.selectImageButton);
        Button submitButton = findViewById(R.id.submitButton);
        selectImageButton.setOnClickListener(view -> openGallery());
        submitButton.setOnClickListener(view -> submitDiagnosis());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void submitDiagnosis() {
        String textInput = diagnosisTextInput.getText().toString();

        if (textInput.isEmpty() && selectedImageUri == null) {
            Toast.makeText(this, "Please enter text or select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Submitting diagnosis...", Toast.LENGTH_SHORT).show();

        submissionStatus.setText("Submitted and awaiting diagnosis");
        submissionStatus.setVisibility(View.VISIBLE);

        diagnosisTextInput.setText("");
        selectedImageUri = null;
    }
}
