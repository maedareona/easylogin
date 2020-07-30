package com.example.easylogin.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easylogin.model.entity.User;

//今回作成しているRepositoryは、userテーブルのEntityにアクセスするためのものなので、
//1つめには「User」を.2つめにはUserテーブルのIDの型である「Long」
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByUserNameAndPassword(String userName, String password);
}
