package id.ac.umn.dimas.week10c_41281;

import android.os.AsyncTask;
import android.util.Log;

public class CustomServiceTask extends AsyncTask<Integer, Integer, Integer> {
    @Override
    protected Integer doInBackground(Integer... integers) {
        int startId = integers[0];
        int n = (int) (Math.random() * 50) + 10;

        try {
            for (int i = 0; i < n; i++) {
                Thread.sleep(200);
                publishProgress(startId, ((int)((100 * i) / (float) n)));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return startId;
    }

    @Override
    protected void onProgressUpdate(Integer... integers) {
        super.onProgressUpdate(integers);

        Log.i("CUSTOMSERVICE", "onStartCommand: " + integers[0] + " berjalan " + integers[1] + " persen");
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        Log.i("CUSTOMSERVICE", "onStartCommand: " + result + " Selesai");
    }
}
