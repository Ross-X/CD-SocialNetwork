package com.campudual.RedSocial;
import java.util.*;
public class friendshipSuggestions {
    public static List<User> suggestFriends(User currentUser, List<User> allUsers) {
        //Amigos sugeridos
        List<User> suggestedFriends = new ArrayList<>();

        //Obtener los amigos actuales del usuario
        List<User> currentFriends= currentUser.getFollowers();

        for (User userToFollow : allUsers) {
            /*Lo primero compara que no sean lo mismo(usuario a seguir y
            usuario actual, lo segundo que no lo sigue y lo tercero
            que lo sigue alguno de los seguidores del usuario actual*/
            if (!userToFollow.equals(currentUser) && !currentFriends.contains(userToFollow) && isFollowedByAnyFriend(userToFollow,currentFriends)) {
                suggestedFriends.add(userToFollow);
            }
        }
        return suggestedFriends;
    }
    // Método para verificar si un usuario es seguido por al menos un amigo del usuario
    private static boolean isFollowedByAnyFriend(User userToFollow, List<User> currentFriends) {
        for (User friend : currentFriends) {
            if (friend.getFollowers().contains(userToFollow)) {
                return true;
            }
        }
        return false;
    }

    public static void listSuggested(User currentUser, List<User> allUsers){
        List<User> suggestedFriends = friendshipSuggestions.suggestFriends(currentUser, allUsers);
        if (!suggestedFriends.isEmpty()) {
            System.out.println("Sugerencias para ti: ");
            for (int i = 0; i < suggestedFriends.size(); i++) {
                System.out.println((i + 1) + ". " + suggestedFriends.get(i).getName());
            }
        } else {
            System.out.println("No hay sugerencias de amistad en este momento.");
        }

    }
}
