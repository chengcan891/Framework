1.gradle引入

```groovy
  javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
```

```gr
implementation 'com.alibaba:arouter-api:1.5.0'
    annotationProcessor 'com.alibaba:arouter-compiler:1.2.2'
```

完整的gradle

```gr
apply plugin: 'com.android.application'

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "ink.chengcan.framework"
        minSdkVersion 16
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'com.google.android.material:material:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.vectordrawable:vectordrawable:1.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation project(path: ':module_home')
    implementation project(path: ':module_view')

    implementation 'com.alibaba:arouter-api:1.5.0'
    annotationProcessor 'com.alibaba:arouter-compiler:1.2.2'
}

```

2.Application中初始化

```java
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
```

3.activity跳转

注意每个path至少有两个，第一个是group，是唯一的，使用moduleName

```java
@Route(path = "/moduleName/aroute")
public class ARouteActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText(getIntent().getStringExtra(CommonRouteKey.MESSAGE));
        setContentView(textView);
    }
}
```

跳转可以传输参数

```java
 ARouter.getInstance().build("/moduleName/aroute").withString(CommonRouteKey.MESSAGE, "t").navigation();
```

4.获取参数

- 一种是普通 Activity 那样 getIntent().getXXX
- 另外一种是使用 @Autowired 注解的方式

```java
//会从intent中获取变量，需要确认？
@Autowired
String name;

//会根据"/home/main"创建变量,如果不是IProvider不知道行不后面做测试？
@Autowired(name = "/home/main")
IHomeProvider homeProvider;

@Override
 protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ARouter.getInstance().inject(this);
}

```

```java
public interface IHomeProvider extends IProvider {
    Fragment getMainHomeFragment();
}
```

```java
@Route(path = "/home/main",name = "home")
public class HomeProvider implements IHomeProvider {
    @Override
    public Fragment getMainHomeFragment() {
        return new MainHomeFragment();
    }

    @Override
    public void init(Context context) {

    }
}
```

