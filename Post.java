package com.campudual.RedSocial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.campudual.util.Input;

public class Post {
    protected LocalDateTime postDateTime;
    protected List<Comment> comments;

    public Post() {
        postDateTime = LocalDateTime.now();
        this.comments = new ArrayList<>();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public LocalDateTime getPostDateTime() {
        return postDateTime;
    }

    //Añadir comentario
    public static int listPosts(List<User> listUser) {
        List<Post> allPosts = Wall.getAllPosts(listUser);
        if (!allPosts.isEmpty()) {
            System.out.println("Selecciona un post para comentar:");
            for (int i = 0; i < allPosts.size(); i++) {
                System.out.println((i + 1) + ". " + allPosts.get(i).toString());
            }
            System.out.print("Ingresa el número del post en el que deseas comentar: ");
            int postNumber = Input.integer();
            return postNumber;

        } else {
            System.out.println("No hay posts disponibles para comentar.");
            return -1; // Devuelve -1 para indicar que no se seleccionó ningún post
        }

    }

    public static void addComment(int postNumber, String text, User author, List<User> listUser) {
        List<Post> allPosts = Wall.getAllPosts(listUser);
        if (postNumber >= 1 && postNumber <= allPosts.size()) {
            Post selectedPost = allPosts.get(postNumber - 1);
            selectedPost.comments.add(new Comment(text, author));
            System.out.println("Commentary agregado con éxito.");
        } else {
            System.out.println("Número de post no válido.");
        }
    }


    public static void listComments(User user) {
        System.out.println("Comentarios de " + user.getName() + ":");

        for (Post post : user.getPosts()) {
            List<Comment> comments = post.getComments();
            int numberOfComments = post.getNumberOfComments();
            if (!comments.isEmpty()){
                for (int i = 0; i < comments.size(); i++) {
                    Comment comment = comments.get(i);
                    System.out.println("Comentario #" + (i + 1));
                    System.out.println("Autor: " + comment.getAuthor().getName());
                    System.out.println("Fecha de creación: " + comment.getDate());
                    System.out.println("Texto: " + comment.getText());
                    System.out.println();
                }
            }else {
                System.out.println("Aun no haz comentado en ningún posts");
            }

        }
    }
    public int getNumberOfComments() {
        return comments.size();
    }


}
