package homework4;

import java.util.*;

public class SocialNetwork {
    private int V;
    private int E;

    private HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {
        // implement the actual logic
        this.adj = new HashMap<>();
    }

    public SocialNetwork(Scanner in) {
        // implement the actual logic
        this.adj = new HashMap<>();
        boolean isFirstLine = true;
        while (in.hasNextLine()) {
            String line = in.nextLine().trim();
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            if (line.isEmpty()) continue;

            String[] data = line.split(";");
            if (data.length != 3) {
                System.out.println("Invalid line skipped: " + line);
                continue;
            }

            String friend1 = data[0];
            String friend2 = data[1];
            int strength;
            try {
                strength = Integer.parseInt(data[2]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid strength value: " + data[2]);
                continue;
            }

            Friendship friendship = new Friendship(friend1, friend2, strength);
            this.addFriendship(friendship);
        }
    }

    public void addUser(String user) {
        // implement the actual logic
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {
        // implement the actual logic
        addUser(f.getFirstFriend());
        addUser(f.getSecondFriend());
        adj.get(f.getFirstFriend()).add(f);
        adj.get(f.getSecondFriend()).add(new Friendship(f.getSecondFriend(), f.getFirstFriend(), f.getFriendshipStrength()));
        E++;
    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {
        // implement the actual logic (remove next line)
        ArrayList<FriendshipRecommendation> recommendations = new ArrayList<>();
        if (!adj.containsKey(user)) return recommendations;

        Map<String, Integer> potentialFriends = new HashMap<>();

        for (Friendship friendShip : adj.get(user)) {
            String friend = friendShip.getSecondFriend();
            int userFriendStrength = friendShip.getFriendshipStrength();

            for (Friendship friendOfFriendShip : adj.get(friend)) {
                String potentialFriend = friendOfFriendShip.getSecondFriend();
                int friendToPotentialFriendStrength = friendOfFriendShip.getFriendshipStrength();

                if (potentialFriend.equals(user) || adj.get(user).stream().anyMatch(f -> f.getSecondFriend().equals(potentialFriend)))
                    continue;

                int minStrength = Math.min(userFriendStrength, friendToPotentialFriendStrength);
                potentialFriends.put(potentialFriend, potentialFriends.getOrDefault(potentialFriend, 0) + minStrength);
            }
        }

        for (Map.Entry<String, Integer> entry : potentialFriends.entrySet()) {
            recommendations.add(new FriendshipRecommendation(entry.getKey(), entry.getValue()));
        }

        Collections.sort(recommendations);
        return recommendations;
    }

    public int getUserCount() {
        return adj.size();
    }

    public int getFriendshipCount() {
        int count = 0;
        for (List<Friendship> friends : adj.values()) {
            count += friends.size();
        }
        return count / 2;
    }

    public boolean hasUser(String user) {
        return adj.containsKey(user);
    }
}
