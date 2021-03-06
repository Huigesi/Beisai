package com.example.userlogin1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener{
	private Button cancel;
	private Button login;
	private EditText usernameET;
	private EditText passwordET;
	private CheckBox jizhu;
	private TextView register;
	private TextView findpwd;
	SharedPreferences preferences;
	Editor editor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}

	private void init() {
		cancel=(Button) findViewById(R.id.cancel);
		login=(Button) findViewById(R.id.login);
		usernameET=(EditText) findViewById(R.id.usernames);
		passwordET=(EditText) findViewById(R.id.userpsw);
		jizhu=(CheckBox)findViewById(R.id.jizhu);
		register=(TextView)findViewById(R.id.register);
		findpwd=(TextView)findViewById(R.id.find);
		/*cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LoginActivity.this,TestActivity.class);
				startActivity(new Intent(intent));
				System.out.println("sss");
				v.getId()
			}
		});*/
		cancel.setOnClickListener(this);
		login.setOnClickListener(this);
		jizhu.setOnClickListener(this);
		register.setOnClickListener(this);
		findpwd.setOnClickListener(this);
		preferences=getSharedPreferences("UserInfo",MODE_PRIVATE);
		editor=preferences.edit();
		String name=preferences.getString("name", null);
		String psw=preferences.getString("psw", null);
		if(name==null||psw==null){
			jizhu.setChecked(false);
		}else {
			//jizhu.setChecked(true);
			usernameET.setText(name);
			passwordET.setText(psw);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.cancel:
				usernameET.setText("");
				passwordET.setText("");
				
				break;
			case R.id.login:
				String username = usernameET.getText().toString().trim();
				String password = passwordET.getText().toString().trim();
				if(username.equals("admin")||password.equals("123456")){
					if(jizhu.isChecked()){
						editor.putString("name", username);
						editor.putString("psw",password);
						editor.commit();
					}else{
						editor.remove("name");
						editor.remove("psw");
						editor.commit();
					}
				}else {
					Toast.makeText(this, "用户名密码不准确", Toast.LENGTH_SHORT).show();
				}
				Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
				break;
			case R.id.jizhu:
				jizhu.isChecked();
				break;
			case R.id.register:
				final RegisterDialog rg=new RegisterDialog(LoginActivity.this);
				rg.setMyCallBack(new RegisterDialog.MyCallBack() {
					
					@Override
					public void ok() {
						String rgusername=rg.rgusernameET.getText().toString();
						String rgpassword=rg.rgpasswordET.getText().toString();
						String rgemail=rg.rgpasswordET.getText().toString();
						String regex1="[0-9]{6,12}+";
						String regex2="[a-zA-Z0-9]{3,6}+";
						String regex3="[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
						if(rgusername.matches(regex1)){
							
						}else{
							Toast.makeText(LoginActivity.this, "用户名必须为6-12位纯字母", Toast.LENGTH_SHORT).show();
						}
						if(rgpassword.matches(regex2)){
							
						}else{
							Toast.makeText(LoginActivity.this, "密码必须为3-6位字母与数字组合", Toast.LENGTH_SHORT).show();
						}
						if(rgemail.matches(regex3)){
							
						}else{
							Toast.makeText(LoginActivity.this, "邮箱格式不准确", Toast.LENGTH_SHORT).show();
						}
						
						
					}
					
					@Override
					public void cancel() {
						rg.dismiss();
					}
				});
				rg.setCancelable(true);
				rg.show();
				break;
			case R.id.find:
				Toast.makeText(this, "已将密码发至邮箱", Toast.LENGTH_SHORT).show();
				break;
		}
		
	}

}
