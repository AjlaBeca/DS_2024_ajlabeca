package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        // main application logic
        Scanner inputScanner = new Scanner(System.in);

        String filePath="social_network.csv";

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File not found: " + filePath);
            return;
        }

        System.out.println("Loading in the social network...");

        SocialNetwork socialNetwork;
        try (Scanner fileScanner = new Scanner(file)) {
            socialNetwork = new SocialNetwork(fileScanner);
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }


        System.out.println("Network successfully loaded.");
        System.out.println("Total users: " + socialNetwork.getUserCount());
        System.out.println("Total friendships: " + socialNetwork.getFriendshipCount());

        System.out.println("==================================================");


        while (true) {
            System.out.println("\nEnter a name to recommend for, or -1 to exit: ");
            String user = inputScanner.nextLine();
            if (user.equals("-1")) {
                System.out.println("Thank you for using our friendship recommender algorithm.\n");
                break;}

            if (socialNetwork.hasUser(user)) {
                ArrayList<FriendshipRecommendation> recommendations = socialNetwork.recommendFriends(user);
                System.out.println("Total recommendations based on friendship strength: " + recommendations.size());
                System.out.println("Top 10 recommendations:");
                for (int i = 0; i < Math.min(10, recommendations.size()); i++) {
                    System.out.println(recommendations.get(i));

                }
            } else {
                System.out.println("The user you are looking for does not exist in the network.");
            }
        }

        inputScanner.close();
    }
}
