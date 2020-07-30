package com.example.easylogin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepos;
	
	//トップページへの遷移を担うindexメソッド。
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
//Stringのデータを戻り値として返す
//下記アノテーションが付与されている2つはクライアントからのリクエストであることを意味する
//HTML側で定義されたname属性を指定することによって判断します。
	@RequestMapping("/login")
	public String login(
		@RequestParam("userName") String userName,
		@RequestParam("password") String password,
		
//Modelはレスポンスとしてクライアント側に返すためのオブジェクト
//今回はadd・・・を使用し得messageというキー文字列に対して、ログイン結果によって分岐する、メッセージを値に設定している
		Model m) {
		
	String message = "Welcome! ";

//userRepositoryに追加したメソッドを呼び出して、Userの一覧を取得している
		List<User> users = userRepos.findByUserNameAndPassword(userName, password);
		if(users.size() > 0) {
			User user = users.get(0);
			message += user.getFullName();
		} else {
			message += "guest";
		}
		
		m.addAttribute("message", message);
		
		return "login";
	}

}
