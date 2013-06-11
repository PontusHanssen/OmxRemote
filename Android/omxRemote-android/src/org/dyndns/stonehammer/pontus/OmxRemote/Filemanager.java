package org.dyndns.stonehammer.pontus.OmxRemote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Filemanager extends Activity {
	List<String> list;
	public final static String PREFS_NAME="Settings_file";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filemanager);
		if (!MainActivity.client.isConnected()) {
			Toast.makeText(getApplicationContext(), "Not connected!",
					Toast.LENGTH_SHORT).show();
			finish();

		}


	    final ListView listview = (ListView) findViewById(R.id.listView1);
	    SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
	    String folder = settings.getString("Folder", "/mnt/temp/");
	    final List<String> list = MainActivity.client.getList(folder);
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        String item = (String) parent.getItemAtPosition(position);
	        MainActivity.client.sendControl("play " + item + "\n");
	        finish();
	      }

	    });
	  }

	  private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }

	} 
