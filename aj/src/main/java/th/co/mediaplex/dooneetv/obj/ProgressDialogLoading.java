package th.co.mediaplex.dooneetv.obj;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Banpot.S on 10/20/14 AD.
 */
public class ProgressDialogLoading extends ProgressDialog{

    public ProgressDialogLoading(Context context) {
        super(context);
        this.setIndeterminate(true);
        this.setCancelable(true);
        this.setInverseBackgroundForced(false);
        this.setCanceledOnTouchOutside(true);
        //this.setTitle("Sending...");
    }
}
