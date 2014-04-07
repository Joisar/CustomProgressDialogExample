package com.kbs.code;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.customprogressdialog.R;

public class MainActivity extends Activity {
  private View textView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = findViewById(R.id.tv1);
  }

  public void doSomethingThatTakesALongTime(View v) {
    new VeryLongAsyncTask(this).execute();
  }

  //
  // ZZZZZ
  //

  class VeryLongAsyncTask extends AsyncTask<Void, Void, Void> {
    private final ProgressDialog progressDialog;

    public VeryLongAsyncTask(Context ctx) {
      progressDialog = MyCustomProgressDialog.ctor(ctx);
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      textView.setVisibility(View.INVISIBLE);

      progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... params) {
      // sleep for 5 seconds
      try { Thread.sleep(5000); }
      catch (InterruptedException e) { e.printStackTrace(); }

      return null;
    }

    @Override
    protected void onPostExecute(Void result) {
      super.onPostExecute(result);
      textView.setVisibility(View.VISIBLE);

      progressDialog.hide();
    }
  }
}
