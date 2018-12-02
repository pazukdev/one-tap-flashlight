package by.pazukdev.SimpleFlashlight;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogService {

    private Context context;
    private AlertDialog alertDialog;


    public AlertDialogService(Context context) {
        this.context = context;
    }


    public AlertDialog getAlertDialog(String alertDialogType) {

        alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(context,
                R.style.MyAlertDialog)).create();

        alertDialog.setCancelable(true);

        if(alertDialogType.equals("Privacy Policy")) createPrivacyPolicyDialog();
        if(alertDialogType.equals("App start error")) createAppStartErrorDialog();
        if(alertDialogType.equals("Camera start error")) createCameraStartErrorDialog();

        alertDialog.show();

        styleDialog(); // style alert dialog to app style

        return alertDialog;
    }

    private void createPrivacyPolicyDialog() {
        alertDialog.setCancelable(false);
        alertDialog.setTitle(context.getResources().getString(R.string.privacy_policy_button));
        alertDialog.setTitle(context.getResources().getString(R.string.privacy_policy_button));
        alertDialog.setIcon(R.drawable.ic_launcher);
        alertDialog.setMessage(context.getResources().getString(R.string.privacy_policy));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                context.getResources().getString(R.string.close),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
    }

    private void createAppStartErrorDialog() {
        alertDialog.setTitle(context.getResources().getString(R.string.app_start_error_title));
        alertDialog.setMessage(context.getResources().getString(R.string.app_start_error_message));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                context.getResources().getString(R.string.close_app), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((AppCompatActivity)context).finish();
                    }
                });

        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(context, context.getResources()
                                .getString(R.string.alertdialog_oncancel_toast_text), Toast.LENGTH_LONG).show();
                alertDialog.show();
            }
        });
    }

    private void createCameraStartErrorDialog() {
        alertDialog.setTitle(context.getResources().getString(R.string.camera_start_error_title));
        alertDialog.setMessage(context.getResources().getString(R.string.camera_statr_error_message));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                context.getResources().getString(R.string.close_app), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((AppCompatActivity)context).finish();
                    }
                });
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(context, context.getResources()
                        .getString(R.string.alertdialog_oncancel_toast_text), Toast.LENGTH_LONG).show();
                alertDialog.show();
            }
        });
    }

    private void styleDialog() {

        // set button and button text colors
        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        button.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTextLight));

        // set title divider color
        int titleDividerId = context.getResources().getIdentifier("titleDivider", "id", "android");
        View titleDivider = alertDialog.findViewById(titleDividerId);
        if (titleDivider != null)
            titleDivider.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

    }

}















