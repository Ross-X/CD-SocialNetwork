package com.campudual.RedSocial;

import java.io.*;

import com.campudual.util.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SocialNetworkApp {

    public static void main(String[] args) {
        showRegister();

    }

    private static void showRegister() {
        List<User> registers = new ArrayList<>();
        String userName;
        int option;
        //Crear usuarios de prueba
        User usuario1 = new User("Rossi");
        User usuario2 = new User("Angel");
        User usuario3 = new User("Claudia");
        registers.add(usuario1);
        registers.add(usuario2);
        registers.add(usuario3);
        usuario1.addFollowUser(usuario2);
        usuario2.addFollowUser(usuario1);
        usuario2.addFollowUser(usuario3);

        //Crear post de pruebas
        Text post1 = new Text("Texto de Rossi", "Texto creado por Rossi");
        usuario1.addPost(post1);
        //Crear comentario de prueba


        //Registro

        while (true) {
            System.out.println("1. Iniciar sesion ");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.println("Escoge una opción: ");
            try {
                option = Input.integer();
                switch (option) {
                    case 1:
                        System.out.print("Ingrese su nombre de usuario: ");
                        userName = Input.string();
                        User foundUserName = foundUser(userName, registers);
                        if (foundUserName != null) {
                            System.out.println("Iniciada sesión con: " + userName);
                            showMenu(foundUserName, registers);
                        } else {
                            System.out.println("Nombre de usuario no encontrado. Intente nuevamente.");
                        }
                        break;
                    case 2:
                        // Registrarse
                        System.out.print("Ingrese el nombre del nuevo usuario: ");
                        userName = Input.string();
                        //Instancia de la clase User
                        if (isUserNameUnique(userName, registers)) {
                            User newUser = new User(userName);
                            registers.add(newUser);
                            System.out.println("Usuario creado con éxito.");

                        } else {
                            System.out.println("El nombre de usuario ya esta en uso");
                        }
                        break;
                    case 0:
                        // Salir
                        System.out.printf("Saliendo del programa");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ingresar un número.");

            }

        }
    }

    private static void showMenu(User currentUser, List<User> listUser) {
        int option;
        boolean stayInMenu = true;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Seguir a un usuario");
            System.out.println("2. Dejar de seguir a un usuario");
            System.out.println("3. Agregar un post");
            System.out.println("4. Listar posts de un usuario");
            System.out.println("5. Eliminar un post");
            System.out.println("6. Añadir comentario");
            System.out.println("7. Mostrar comentarios de un usuario");
            System.out.println("8. Eliminar usuario");
            System.out.println("0. Volver al menú anterior.");
            System.out.print("Seleccione una opción: ");
            try {
                option = Input.integer();
                switch (option) {
                    case 1:
                        //Seguir a un usuario
                        //Mostrar usuarios registrados
                        listRegisterUsers(listUser);
                        //Mostrar usuarios sugeridos
                        friendshipSuggestions.listSuggested(currentUser, listUser);
                        System.out.println("Escribe el nombre del usuario al que quieres seguir: ");
                        String followUserName = Input.string();
                        //Saber si el nombre del uduario al que se quiere seguir
                        // esta registrado
                        User followUser = foundUser(followUserName, listUser);
                        //Si lo esta
                        if (followUser != null) {
                            if (currentUser.equals(followUser)) {
                                System.out.println("No te puedes seguir a ti mismo");
                            } else {
                                currentUser.addFollowUser(followUser);
                                System.out.println("Ahora sigues a: " + followUserName);
                            }

                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                        break;
                    case 2:
                        //Dejar de seguir a un usuario
                        //Mostrar usuarios a los que sigue
                        currentUser.showFollowedUsers();
                        //Escribir nombre del usuario al que quiere dejar de seguir
                        System.out.println("Escribe el nombre del usuario al que " +
                                "quieres dejar de seguir: ");
                        String unfollowUserName = Input.string();
                        //Guardar nombre del usuario al que quiere dejar de seguir
                        //despues de comprobar si esta registrado
                        User unfollowUser = foundUser(unfollowUserName, listUser);
                        //Si esta registrado ahora tendria que buscar si lo sigue
                        List<User> yourFollowers = currentUser.getFollowers();

                        if (unfollowUser != null) {
                            if (yourFollowers.contains(unfollowUser)) {
                                currentUser.unfollowUser(unfollowUser);
                                System.out.println("Dejaste de seguir a: " + unfollowUserName);
                            } else {
                                System.out.println("No estas siguiendo a nadie con el nombre: " + unfollowUserName);
                            }
                        } else {

                            System.out.println("Usuario no encontrado.");
                        }
                        break;
                    case 3:
                        // agregar un post
                        createPostMenu(currentUser);
                        break;
                    case 4:
                        // Listar posts de un usuario
                        System.out.println("\nPosts creados: \n");
                        currentUser.showPosts(currentUser);
                        break;
                    case 5:
                        //Eliminar un post
                        currentUser.deletePost(currentUser);
                        break;
                    case 6:
                        //Crear comentario
                        int postNumber = Post.listPosts(listUser);
                        System.out.print("Ingresa el texto del comentario: ");
                        String commentText = Input.string();
                        Post.addComment(postNumber, commentText, currentUser, listUser);
                        break;
                    case 7:
                        // mostrar comentarios
                        Post.listComments(currentUser);
                        break;
                    case 8:
                        //Eliminar USUARIO
                        System.out.println("Estas seguro de que quieres eliminar tu usuarios Sí(S)/No(N): ");
                        String choice = Input.string();
                        if (choice.equals("S")) {
                            currentUser.deleteUser(currentUser, listUser);
                            // Volver al menú de registro

                            stayInMenu = false;
                        }


                        break;
                    case 0:
                        stayInMenu = false;
                        break;
                    default:

                        System.out.println("Opción no válida. Intente nuevamente.");

                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ingresar un número.");

            }

        } while (stayInMenu);
    }

    //Para saber si el usuario esta registrado
    private static User foundUser(String userName, List<User> listUser) {
        for (User user : listUser) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null; // Usuario no encontrado
    }


    private static void createPostMenu(User currentUser) {
        int op;
        System.out.println("1. Escribir");
        System.out.println("2. Subir una imagen");
        System.out.println("3. Subir un video");
        op = Input.integer();
        switch (op) {
            case 1:
                // Crear una publicación de texto
                System.out.println("Escribe el titulo de tu publicación:");
                String title = Input.string();
                System.out.println("Escribe el contenido de tu publicación:");
                String textContent = Input.string();
                Post textPost = new Text(title, textContent);
                currentUser.addPost(textPost); // Agregar la publicación a la lista de publicaciones del usuario actual
                System.out.println("Post de texto creada con éxito.");
                break;
            case 2:
                // Crear una publicación de imagen
                System.out.println("Escribe un título para la imagen:");
                String imageTitle = Input.string();
                System.out.println("Especifica el ancho de la imagen:");
                int imageWidth = Input.integer();
                System.out.println("Especifica la altura de la imagen:");
                int imageHeight = Input.integer();
                Post imagePost = new Image(imageTitle, imageWidth, imageHeight);
                currentUser.addPost(imagePost); // Agregar la publicación a la lista de publicaciones del usuario actual
                System.out.println("Post de imagen creada con éxito.");
                break;
            case 3:
                // Crear una publicación de video
                System.out.println("Escribe un título para el video:");
                String videoTitle = Input.string();
                System.out.println("Indica la calidad del video: ");
                String videoQualityquality = Input.string();
                System.out.println("Especifica la duración del video (en segundos):");
                int videoDuration = Input.integer();
                Post videoPost = new Video(videoTitle, videoQualityquality, videoDuration);
                currentUser.addPost(videoPost); // Agregar la publicación a la lista de publicaciones del usuario actual
                System.out.println("Post de video creada con éxito.");
                break;
            default:
                break;


        }


    }


    private static void listRegisterUsers(List<User> listUser) {
        if (listUser.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (User user : listUser) {
                System.out.println(user.getName());
            }
        }
    }

    private static boolean isUserNameUnique(String userName, List<User> registers) {
        for (User user : registers) {
            if (user.getName().equals(userName)) {
                return false; // El nombre de usuario ya está en uso
            }
        }
        return true; // El nombre de usuario es único
    }

    private static void createCommentMenu() {
        System.out.println("Escoge en que post quieres dejar un comentario:\n");

    }

}

