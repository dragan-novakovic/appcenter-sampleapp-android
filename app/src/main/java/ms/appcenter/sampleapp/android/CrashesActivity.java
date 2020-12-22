package ms.appcenter.sampleapp.android;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import java.lang.Math;
import java.util.Random;

public class CrashesActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.crashes_root, container, false);

        Button crashButton = rootView.findViewById(R.id.crashButton);
        crashButton.setOnClickListener(view -> {
            DialogFragment crashDialog = new CrashDialog();
            crashDialog.show(getFragmentManager(), "crashDialog");
        });

        return rootView;
    }

    public static class CrashDialog extends DialogFragment {
        Random rand = new Random();
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Generate random integers in range 0 to 9
            int rand_int = rand.nextInt(50);
            System.out.println("Random Integers: "+rand_int);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("A crash report will be sent when you reopen the app.")
                    .setPositiveButton("Crash app (" + String.valueOf(rand_int) + ")", (dialog, id) -> {
                        throw new RuntimeException("crashing " + String.valueOf(rand_int));
                    }).setNegativeButton("Cancel", (dialog, id) -> {
                // Add any code you'd like to execute when users click "Cancel"
            });
            return builder.create();
        }
    }
}
