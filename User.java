package com.campudual.RedSocial;

import com.campudual.util.Input;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class User {
    protected String name;
    protected List<User> followers;
    protected List<Post> posts;

    public User(String name) {
        this.name = name;
        this.followers = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    //Obtener nombres de USUARIO

    public String getName() {
        return name;
    }

    //Eliminar usuario
    public void deleteUser(User currentUser, List<User> listUser) {
        // Elimina al usuario de las listas de seguidos de otros usuarios
        for (User user : listUser) {
            if (user.getFollowers().contains(currentUser)) {
                user.unfollowUser(currentUser);
            }
        }
        // Eliminar los comentarios del usuario
        for (User user : listUser) {
            for (Post post : user.getPosts()) {
                List<Comment> commentsToRemove = new ArrayList<>();
                for (Comment comment : post.getComments()) {
                    if (comment.getAuthor().equals(currentUser)) {
                        commentsToRemove.add(comment);
                    }
                }
                post.getComments().removeAll(commentsToRemove);
            }
        }

        // Eliminar los posts del usuario
        currentUser.getPosts().clear(); // Limpiar la lista de posts del usuario

        //Obtengo un iterator de mi lista de registros usando
        //el metodo iterator
        Iterator<User> iterator = listUser.iterator();
        //Recorro la lista usando el Iterator
        while (iterator.hasNext()) {
            //Con esto estoy teniendo el nombre que quiero
            //eliminar
            User user = iterator.next();
            if (currentUser.getName().equals(user.getName())) {
                iterator.remove();
            }
        }
    }

    //Añadir y remover SEGUIDORES
    public void addFollowUser(User user) {
        if (!this.equals(user)) {// Evitar que un usuario se siga a sí mismo
            followers.add(user);
        }
    }

    public void unfollowUser(User user) {
        followers.remove(user);
    }

    //Obtener SEGUIDORES
    public List<User> getFollowers() {
        return followers;

    }

    // Método para mostrar los usuarios a los que sigue este usuario
    public void showFollowedUsers() {
        if (followers.isEmpty()) {
            System.out.println("No sigues a ningún usuario aún.");
        } else {
            System.out.println("Usuarios que sigues:");
            for (User followedUser : followers) {
                System.out.println(followedUser.getName());
            }
        }
    }

    //Añadir y eliminar POST
    public void addPost(Post post) {
        posts.add(post);
    }

    //Mostrar posts
    public void showPosts(User currentUser) {
        List<Post> userPost = currentUser.getPosts();
        for (int i = 0; i < userPost.size(); i++) {
            String postCont = userPost.get(i).toString();
            System.out.println((i + 1) + "." + postCont);
        }
    }

    //Eliminar posts
    public void deletePost(User currentUser) {
        System.out.println("Qué post quieres eliminar: ");
        showPosts(currentUser);
        System.out.print("Ingresa el número del post que quieres eliminar: ");
        int postNumber = Input.integer();
        List<Post> userPosts = currentUser.getPosts();
        if (postNumber >= 1 && postNumber <= userPosts.size()) {
            Post postToDelete = userPosts.get(postNumber - 1);
            currentUser.posts.remove(postToDelete);
            System.out.println("El post ha sido eliminado exitosamente.");
        } else {
            System.out.println("Número de post no válido.");
        }
    }


    //Obtener POST
    public List<Post> getPosts() {
        return posts;
    }


}
