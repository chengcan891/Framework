https://www.jianshu.com/p/05ef48b777cc

这里主要注意onCreateOptionsMenu，不然不显示

```java
public class ExtendToolbarActivity extends BaseActivity {

    @Override
    public int getLayoutResource() {
        return R.layout.home_activity_extend_toolbar;
    }

    @Override
    public void initView() {
        super.initView();
        Toolbar toolbar = findViewById(R.id.toolbar1);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setTitle("Extend");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}

```

