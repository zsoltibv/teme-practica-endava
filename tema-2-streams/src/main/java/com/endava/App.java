package com.endava;

import com.endava.datasource.UserDataSource;
import com.endava.model.User;
import com.endava.sport.Basketball;
import com.endava.sport.Football;
import com.endava.sport.Sport;
import com.endava.sport.Tennis;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class App 
{
    public static void main( String[] args )
    {
        UserDataSource dataSource = new UserDataSource();

        // -- Interface default methods --
//        Sport football = new Football();
//        Sport basket = new Basketball();
//        Sport tennis = new Tennis();
//
//        football.beginGame();
//        Sport.encourage();

        // -- Functional Interfaces --
//        Function<Integer, String> integerToStringFunction = new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) {
//                return String.valueOf(integer);
//            }
//        };

//        Function<Integer, String> intToStringLambda =  i -> String.valueOf(i);
//
//        Predicate<Integer> myPredicate = i -> i>10;
//
//        System.out.println(myPredicate.test(12));

//        Comparator<Integer> comparator = (first, second) -> {
//            if(first< second){
//                return -1;
//            } else if (first>second) {
//                return 1;
//            }
//            return 0;
//        };

//        dataSource.getStudents().forEach(user -> System.out.println(user));
//        dataSource.getUsersWithAgeLessThan30().forEach(user -> System.out.println(user));
//        dataSource.getUserFirstNamesWithJobGamer().forEach(firstName -> System.out.println(firstName));
//        System.out.println(dataSource.sumUpUserAgesWhereFirstNameStartsWithJ());
//        System.out.println(dataSource.findOldestUser());

        // -- Optional --
        Optional<User> userOptional = dataSource.getUserById(12);
//        if(userOptional.isPresent()){
//            System.out.println(userOptional.get().getFirstName());
//        }else {
//            System.out.println("User with id " + 1 + "was not found");
//        }

//        userOptional.ifPresent(user -> System.out.println(user.getFirstName()))
//        System.out.println( userOptional.orElseGet(User::new).getFirstName());

        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
//        userOptional.orElseThrow(() -> new RuntimeException("User not found"));

    }
}
