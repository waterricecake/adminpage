package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    //Defendency Injection (DI)

    @Autowired

    private UserRepository userRepository;
    @Test
    public void create(){
        // String sql = insert into user(%s,%s,%d) value (account,email,age);
        User user = new User(); //
        //user.setId();  // Id는 NN에 auto 이기 때문에 선언안해도됨
        user.setAccount("TestUser03");
        user.setEmail("TestUser03gmail.com");
        user.setPhoneNumber("010-3333-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");


        User newUser = userRepository.save(user);
        System.out.println("newUser: "  + newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findByAccount("TestUser03");


        user.ifPresent(selectUser ->{
            selectUser.getOrderDetailList().stream().forEach(detail ->{
                Item item = detail.getItem();
                System.out.println(detail.getItem());


            });
        });


    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            selectUser.setAccount("ppppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("Update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional
    public void delete(){

        Optional<User> user = userRepository.findById(2L);


        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);//void
        });

        Optional<User> deleteUser = userRepository.findById(2L);

        if(deleteUser.isPresent()) {
            System.out.println("데이터 존제 /: " + deleteUser.get());
        }else{
            System.out.println("데이터삭제");
        }
    }

}
