package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import org.junit.jupiter.api.Assertions;
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
        String account = "Test01";
        String password = "Test01";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-1111";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User u = User.builder().
                account(account).
                password(password).
                status(status).
                email(email).
                build();


        User newUser = userRepository.save(user);
        User newU = userRepository.save(u);

        Assertions.assertNotNull(newUser);

    }


    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByIdOrderByIdDesc(1L);
        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("--------------주문묶음---------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총금액 : " + orderGroup.getTotalPrice());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());

            System.out.println("--------------주문상세---------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " +orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());

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
