package tw.hd.com.recodtime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ItemMain> itemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //取得arraylist 存入list中
        itemArray = new ArrayList<>();
        String[] temparray = getResources().getStringArray(R.array.icon_main);
        itemArray.add(new ItemMain(R.drawable.hdd,temparray[0]));
        itemArray.add(new ItemMain(R.drawable.day,temparray[1]));
        itemArray.add(new ItemMain(R.drawable.description,temparray[2]));
        itemArray.add(new ItemMain(R.drawable.about,temparray[3]));
        ItemAdapter itemAdapter = new ItemAdapter();
        recyclerView.setAdapter(itemAdapter);
    }
  public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
      @NonNull
      @Override
      public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
          View view = getLayoutInflater().inflate(R.layout.item_icon,viewGroup,false);
          return new ItemHolder(view);
      }

      @Override
      public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
            final ItemMain itemMain = itemArray.get(i);
            itemHolder.textView.setText(itemMain.getIconName());
            itemHolder.imageView.setImageResource(itemMain.getIconImage());
            itemHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemChick(itemMain);
                }
            });

      }

      @Override
      public int getItemCount() {
          return itemArray.size();
      }

      public  class ItemHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;
            public ItemHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_icon);
                textView = itemView.findViewById(R.id.text_icon);
            }
        }
  }

    private void itemChick(ItemMain itemMain) {
            switch (itemMain.getIconImage()){
                case R.drawable.hdd:
                    startActivity(new Intent(MainActivity.this,ByHddActivity.class));
                    System.out.println("hdd"+ itemMain.getIconName());
                    break;
                case R.drawable.day:
                    startActivity(new Intent(MainActivity.this,ByDayActivity.class));
                    System.out.println("day" + itemMain.getIconName());
                    break;
                case R.drawable.description:
                    startActivity(new Intent(MainActivity.this,HistoryActivity.class));
                    System.out.println("description"+ itemMain.getIconName());
                    break;
                case R.drawable.about:
                    startActivity(new Intent(MainActivity.this,AboutActivity.class));
                    System.out.println("about"+ itemMain.getIconName());
                    break;

            }
    }
}
