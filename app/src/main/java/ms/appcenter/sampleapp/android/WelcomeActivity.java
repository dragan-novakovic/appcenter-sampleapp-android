package ms.appcenter.sampleapp.android;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;

public class WelcomeActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.welcome_root, container, false);
        Button crashButton = rootView.findViewById(R.id.crashButton);
        crashButton.setOnClickListener(view -> {
            DialogFragment crashDialog = new MyCrashDialog();
            crashDialog.show(getFragmentManager(), "crashDialog");
        });
        return rootView;
    }

    public static class MyCrashDialog extends DialogFragment {
        @RequiresApi(api = Build.VERSION_CODES.O)
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("A crash report will be sent when you reopen the app.")
                    .setPositiveButton("Crash app", (dialog, id) -> {
                        int seconds = LocalDateTime.now().getSecond();
                        System.out.println("Random seconds: "+seconds);
                        throw new RuntimeException("crashing " + String.valueOf(seconds));
                    }).setNegativeButton("Cancel", (dialog, id) -> {
                // Add any code you'd like to execute when users click "Cancel"
            });
            return builder.create();
        }
    }
}
