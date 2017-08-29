package fanjh.mine.recyclerviewtab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import fanjh.mine.recyclerviewtab.indicator.MainActivity;
import fanjh.mine.recyclerviewtab.tab.TabActivity;

/**
 * Created by faker on 2017/8/29.
 */

public class EntryActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Button indicator = (Button) findViewById(R.id.btn_to_indicator);
        Button tab = (Button) findViewById(R.id.btn_to_tab);
        indicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryActivity.this, TabActivity.class);
                startActivity(intent);
            }
        });
    }
}
