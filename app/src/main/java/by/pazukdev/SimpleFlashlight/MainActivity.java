package by.pazukdev.SimpleFlashlight;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

/**
 * @author PazukDev
 */
public class MainActivity extends AppCompatActivity {

    private boolean hasFlash;
    private Camera camera; // deprecated class Camera will be changed to CameraDevice at one of
                           // the next updates if it increase min Android version up to 21
    private Parameters parameters;
    private Button button;
    private AlertDialogService alertDialogService = new AlertDialogService(this);

    private void hasFlashCheck() {
        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!hasFlash) alertDialogService.getAlertDialog("App start error");
        else {
            try {
                camera = Camera.open();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
                Log.e("Error, can't start: ", e.getMessage());
                alertDialogService.getAlertDialog("Camera start error");
            }
        }

    }

    private void getCamera() {
        try {
            if(camera != null) parameters = camera.getParameters();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
            Log.e("Error, can't start: ", e.getMessage());
            alertDialogService.getAlertDialog("Camera start error");
        }
    }

    private void flashOn() {
        if (parameters != null) {
            List supportedFlashModes = parameters.getSupportedFlashModes();
            if(supportedFlashModes.contains(Parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
            }
            else if(supportedFlashModes.contains(Parameters.FLASH_MODE_ON)) {
                parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
            }
            else camera = null;
            if(camera != null) {
                camera.setParameters(parameters);
                camera.startPreview();
            }
        }
    }

    private void  flashOff() {
        parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
        camera.stopPreview();
        camera.release();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        hasFlashCheck();
        getCamera();
        flashOn();

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogService.getAlertDialog("Privacy Policy");

                // uncomment for test
                //alertDialogService.getAlertDialog("App start error");
                //alertDialogService.getAlertDialog("Camera start error");
            }
        });

    }

    @Override
    protected void onDestroy() {
        flashOff();
        super.onDestroy();

    }
}













