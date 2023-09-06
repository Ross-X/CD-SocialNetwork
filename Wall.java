package com.campudual.RedSocial;

import java.util.*;

public class Wall {

    //Supongo que los usuarios podran comentar en cualquier post
    //Entonces consigo todos los post y los muestro para que ellos
    //decidan que comentar

    public static List<Post> getAllPosts(List<User> allUsers) {
        List<Post> allPosts = new ArrayList<>();
        //Necesitaba conseguir cada usuario de todos los registrados
        //Como son todos los posts lo cree en la clase muro
        for (User user :
                allUsers) {
            allPosts.addAll(user.getPosts());
        }
        return allPosts;
    }

}
