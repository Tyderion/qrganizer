package ch.isageek.tyderion.qrganizer;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import ch.isageek.ch.tyderion.qrorganizer.dao.DaoMaster;
import ch.isageek.ch.tyderion.qrorganizer.dao.DaoSession;
import ch.isageek.ch.tyderion.qrorganizer.dao.User;
import ch.isageek.ch.tyderion.qrorganizer.dao.UserDao;


public class MainActivity extends Activity {

    private DaoMaster.DevOpenHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        database = new DaoMaster.DevOpenHelper(this, getString(R.string.dbname), null);
        this.createDemoUserIfNotExists();
        //        this.getDBSession().getUserDao().
    }


    private void createDemoUserIfNotExists() {
        User demoUser = new User(null, getString(R.string.demo_username), getString(R.string.demo_password));
        DaoSession session =  this.getDBSession();
        UserDao userdao = session.getUserDao();
        if (userdao.getKey(demoUser) == null) {
            session.getUserDao().insert(demoUser);
        }
    }

    private DaoSession getDBSession() {
      DaoMaster master = new DaoMaster(this.database.getWritableDatabase());
      return master.newSession();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Log.i("test", getString(R.string.test));
            return rootView;
        }
    }
}
