package sg.edu.rp.c346.bookxchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public class MyBookActivity extends AppCompatActivity {

    ListView lvBooks;

    int[] IMAGES = {};
    String[] TITLE = {};
    String[] AUTHOR = {};
    String[] GENRE = {};
    String[] DESCRIPTION = {};
    String[] CONTACT = {};
    String[] POC = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);

        lvBooks = (ListView)findViewById(R.id.lvBooks);

    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }
}
