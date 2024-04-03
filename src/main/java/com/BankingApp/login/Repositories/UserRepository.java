package com.BankingApp.login.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BankingApp.login.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel,UUID> {
    UserModel findByUserName(String userName);
}
