package com.endava;

import com.endava.datasource.UserDataSource;
import com.endava.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

public class HomeworkTests {
    private UserDataSource dataSource;

    @BeforeEach
    public void setup(){
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John", "Wick", 35, "actor"));
        users.add(new User(2, "Jayce", "Lucas", 35, "driver"));
        users.add(new User(3, "Jack", "Spades", 18, "gamer"));
        users.add(new User(4, "Doug", "Rain", 55, "chef"));
        users.add(new User(5, "Lena", "Sunday", 12, "student"));
        users.add(new User(6, "Missy", "Cooper", 23, "actor"));
        users.add(new User(7, "Mark", "John", 17, "student"));
        dataSource = new UserDataSource(users);
    }

    @Test
    public void testGetUserById(){
        int id = 1;
        User expected = new User(1, "John", "Wick", 35, "actor");
//        User actual = dataSource.getUserById(id).orElseGet(User::new);
        Optional<User> userOptional = dataSource.getUserById(id);
        userOptional.ifPresent(user -> Assertions.assertEquals(expected, user));
    }

    @Test
    public void testGetUserByIdNull(){
        int id = 1;
        User expected = new User(1, "John", "Wick", 35, "actor");
        User actual = dataSource.getUserByIdNull(id);
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void testGetUserByIdUsingStreams(){
        int id = 1;
        User expected = new User(1, "John", "Wick", 35, "actor");
        User actual = dataSource.getUserByIdUsingStreams(id).orElseGet(User::new);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetStudents(){
        List<User> expected = List.of(
                new User(5, "Lena", "Sunday", 12, "student"),
                new User(7, "Mark", "John", 17, "student")
        );

        List<User> actual = dataSource.getStudents();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetUsersWithAgeLessThan30(){
        List<User> expected = List.of(
                new User(3, "Jack", "Spades", 18, "gamer"),
                new User(5, "Lena", "Sunday", 12, "student"),
                new User(6, "Missy", "Cooper", 23, "actor"),
                new User(7, "Mark", "John", 17, "student")
        );
        List<User> actual = dataSource.getUsersWithAgeLessThan30();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetUserFirstNamesWithJobGamer(){
        List<String> expected = List.of("Jack");
        List<String> actual = dataSource.getUserFirstNamesWithJobGamer();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSumUpUserAgesWhereFirstNameStartsWithJ(){
        int expected = 88;
        int actual = dataSource.sumUpUserAgesWhereFirstNameStartsWithJ();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindOldestUser(){
        User expected = new User(4, "Doug", "Rain", 55, "chef");
        User actual = dataSource.findOldestUser();
        Assertions.assertEquals(expected, actual);
    }

    // <---------- TO DO ---------->

    @Test
    public void testGetFullNames(){
        List<String> expected = List.of("John Wick", "Jayce Lucas", "Jack Spades", "Doug Rain", "Lena Sunday", "Missy Cooper", "Mark John");
        List<String> actual = dataSource.getFullNames();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetJobOfTheOldestUser(){
        String expected = "chef";
        String actual = dataSource.getJobOfTheOldestUser();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUserJobsSorted(){
        Set<String> expected = Set.of("actor", "chef", "driver", "gamer", "student");
        Set<String> actual = dataSource.getAllUserJobsSorted();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllUsersAndChangeTheJobForYoungerOnes(){
        List<User> expected = List.of(
                new User(1, "John", "Wick", 35, "actor"),
                new User(2, "Jayce", "Lucas", 35, "driver"),
                new User(3, "Jack", "Spades", 18, "unemployed"),
                new User(4, "Doug", "Rain", 55, "chef"),
                new User(5, "Lena", "Sunday", 12, "unemployed"),
                new User(6, "Missy", "Cooper", 23, "actor"),
                new User(7, "Mark", "John", 17, "unemployed")
        );
        List<User> actual = dataSource.getAllUsersAndChangeTheJobForYoungerOnes();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testFindByFirstName(){
        // should find Jayce
        User expected = new User(2, "Jayce", "Lucas", 35, "driver");
        User actual = dataSource.findByFirstName("Jayce");
        Assertions.assertEquals(expected, actual);

        // shouldn't find Richard
        Assertions.assertThrows(RuntimeException.class, () -> dataSource.findByFirstName("Richard"));
    }

    @Test
    public void testAreAllUsersOlderThan(){
        int age = 10;
        boolean actual = dataSource.areAllUsersOlderThan(age);
        Assertions.assertTrue(actual);

        age = 20;
        actual = dataSource.areAllUsersOlderThan(age);
        Assertions.assertFalse(actual);
    }

    @Test
    public void testAddUser(){
        User user = new User(2, "Jay", "Lee", 25, "teacher");
        Assertions.assertThrows(RuntimeException.class,
                () -> dataSource.addUser(user));

        user.setId(10);
        Assertions.assertDoesNotThrow(() -> dataSource.addUser(user));
    }

    @Test
    public void testChangeAllStudentsJobsAndAges(){
        List<User> expected = List.of(
                new User(1, "John", "Wick", 35, "actor"),
                new User(2, "Jayce", "Lucas", 35, "driver"),
                new User(3, "Jack", "Spades", 18, "gamer"),
                new User(4, "Doug", "Rain", 55, "chef"),
                new User(5, "Lena", "Sunday", 17, "graduate"),
                new User(6, "Missy", "Cooper", 23, "actor"),
                new User(7, "Mark", "John", 22, "graduate")
        );
        dataSource.changeAllStudentsJobsAndAges();
        List<User> actual = dataSource.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCountUsersHavingTheSpecifiedJob(){
        String job = "actor";
        int expected = 2;
        int actual = dataSource.countUsersHavingTheSpecifiedJob(job);
        Assertions.assertEquals(expected, actual);

        job = "gamer";
        expected = 1;
        actual = dataSource.countUsersHavingTheSpecifiedJob(job);
        Assertions.assertEquals(expected, actual);

        job = "engineer";
        expected = 0;
        actual = dataSource.countUsersHavingTheSpecifiedJob(job);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getGetMapOfUsers(){
        Map<Integer, User> expected = Map.of(
                1, new User(1, "John", "Wick", 35, "actor"),
                2, new User(2, "Jayce", "Lucas", 35, "driver"),
                3, new User(3, "Jack", "Spades", 18, "gamer"),
                4, new User(4, "Doug", "Rain", 55, "chef"),
                5, new User(5, "Lena", "Sunday", 12, "student"),
                6, new User(6, "Missy", "Cooper", 23, "actor"),
                7, new User(7, "Mark", "John", 17, "student")
        );
        Map<Integer, User> actual = dataSource.getMapOfUsers();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetPredicateForFilteringName(){
        String name = "John";
        Predicate<User> predicate = dataSource.getPredicateForFilteringByName(name);
        List<User> expected = List.of(
                new User(1, "John", "Wick", 35, "actor"),
                new User(7, "Mark", "John", 17, "student")
        );
        List<User> actual = dataSource.filterUsers(predicate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSortUsers(){
        Comparator<User> comparator = dataSource.getUserComparator();
        List<User> expected = List.of(
                new User(5, "Lena", "Sunday", 12, "student"),
                new User(7, "Mark", "John", 17, "student"),
                new User(3, "Jack", "Spades", 18, "gamer"),
                new User(6, "Missy", "Cooper", 23, "actor"),
                new User(1, "John", "Wick", 35, "actor"),
                new User(2, "Jayce", "Lucas", 35, "driver"),
                new User(4, "Doug", "Rain", 55, "chef")
        );
        List<User> actual = dataSource.sortUsers(comparator);
        Assertions.assertEquals(expected, actual);
    }
}
