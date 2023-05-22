package cc.hiifong.androidwork.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import cc.hiifong.androidwork.R;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private Button btn_back,btn_go;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.wv);
        btn_go = findViewById(R.id.go);
        btn_back = findViewById(R.id.back);
        editText = findViewById(R.id.et);
        webView.requestFocus();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request == null) {
                    return false;
                }
                try {
                    if (request.getUrl().toString().startsWith("weixin://") || request.getUrl().toString().startsWith("alipays://")){
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(request.getUrl().toString()));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }
        });
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("https://" + editText.getText().toString().trim());
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });
    }
}