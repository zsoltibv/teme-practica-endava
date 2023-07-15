package com.endava.datasource;

import com.endava.model.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDataSource {

    private List<User> users = new ArrayList<>();

    public UserDataSource() {
        users.add(new User(1, "John", "Wick", 35, "actor"));
        users.add(new User(2, "Jayce", "Lucas", 35, "driver"));
        users.add(new User(3, "Jack", "Spades", 18, "gamer"));
        users.add(new User(4, "Doug", "Rain", 55, "chef"));
        users.add(new User(5, "Lena", "Sunday", 12, "student"));
        users.add(new User(6, "Missy", "Cooper", 23, "actor"));
        users.add(new User(7, "Mark", "John", 17, "student"));
    }

    public UserDataSource(List<User> users) {
        this.users = users;
    }

    public List<User> getAll() {
        return users;
    }

    public User getUserByIdNull(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public Optional<User> getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public Optional<User> getUserByIdUsingStreams(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public List<User> getStudents() {
        return users.stream()
                .filter(user -> user.getJob().equals("student"))
                .toList();
    }

    public List<User> getUsersWithAgeLessThan30() {
        return users.stream()
                .filter(user -> user.getAge() < 30)
                .toList();
    }

    public List<String> getUserFirstNamesWithJobGamer() {
        return users.stream()
                .filter(user -> user.getJob().equals("gamer"))
                .map(User::getFirstName)
                .toList();
    }

    public int sumUpUserAgesWhereFirstNameStartsWithJ() {
        return users.stream()
                .filter(u -> u.getFirstName().startsWith("J"))
                .map(User::getAge)
                .reduce(0, Integer::sum);
    }

    public User findOldestUser() {
        return users.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getAge(), u1.getAge()))
                .findFirst()
                .get();
    }

    // <---------- TO DO ---------->

    // Get the full names for all users
    public List<String> getFullNames() {
        // your code here
        return users.stream()
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .toList();
    }

    // Get the job of the oldest user
    public String getJobOfTheOldestUser() {
        // your code here
        return users.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getAge(), u1.getAge()))
                .map(User::getJob)
                .findFirst()
                .get();
    }

    // Get user (distinct) jobs sorted alphabetically
    public Set<String> getAllUserJobsSorted() {
        // your code here
        return users.stream()
                .sorted((u1, u2) -> CharSequence.compare(u2.getJob(), u1.getJob()))
                .map(User::getJob)
                .collect(Collectors.toSet());
    }

    // Get all users and change their job to 'unemployed' if their age is <= 18
    public List<User> getAllUsersAndChangeTheJobForYoungerOnes() {
        // your code here
        return users.stream()
                .peek(user -> {
                    if (user.getAge() <= 18) {
                        user.setJob("unemployed");
                    }
                })
                .toList();
    }

    // Find user by first name - throw RuntimeException if not found
    public User findByFirstName(String firstName) {
        // your code here
        return users.stream()
                .filter(u -> u.getFirstName().equals(firstName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with the given first name: " + firstName));
    }

    // Check if all users are older than the specified age
    public boolean areAllUsersOlderThan(int age) {
        // your code here - please try with allMatch/noneMatch
        return users.stream()
                .allMatch(u -> u.getAge() > age);
    }

    // Add a new user - if there is a user with the same id, don't add and throw a RuntimeException
    public void addUser(User user) {
        // your code here - HINT: use ifPresent() method from Optional
        Optional<User> existingUser = users.stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst();

        if (existingUser.isPresent()) {
            throw new RuntimeException("User with the same ID already exists");
        } else {
            users.add(user);
        }
    }

    // For all students (user.job = "student"), change the job to "graduate" and add 5 years to their age
    public void changeAllStudentsJobsAndAges() {
        // your code here
        users.stream()
                .filter(u -> u.getJob().equals("student"))
                .forEach(u -> {
                    u.setJob("graduate");
                    u.setAge(u.getAge() + 5);
                });
    }

    // Count users that have the given Job
    public int countUsersHavingTheSpecifiedJob(String job) {
        // your code here
        return (int) users.stream()
                .filter(u -> u.getJob().equals(job))
                .count();
    }

    // Get a map where the key is the user id and the value is the User object itself
    public Map<Integer, User> getMapOfUsers() {
        // your code here
        return users.stream()
                .collect(Collectors.toMap(User::getId, u -> u));
    }

    // Get a predicate for filtering by the given name - applies to both firstName and lastName
    public Predicate<User> getPredicateForFilteringByName(String name) {
        // your code here
        return u -> u.getFirstName().equalsIgnoreCase(name) || u.getLastName().equalsIgnoreCase(name);
    }

    // Get a comparator for User type - compare by age ascending, then by job alphabetically
    public Comparator<User> getUserComparator() {
        // your code here
        return Comparator.comparingInt(User::getAge)
                .thenComparing(User::getJob, String.CASE_INSENSITIVE_ORDER);
    }

    // Filter users using the given Predicate
    public List<User> filterUsers(Predicate<? super User> predicate) {
        return users.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    // Sort users using the given Comparator
    public List<User> sortUsers(Comparator<? super User> comparator) {
        return users.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
