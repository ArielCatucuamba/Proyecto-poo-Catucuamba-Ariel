import javax.swing.*;
import java.awt.*;

public class DialogoPersonalizado {

    /**
     * Muestra un JOptionPane con una imagen personalizada.
     */
    public static void mostrarDialogo() {
        // Configurar el boton


        UIManager.put("OptionPane.background",new Color(144, 238, 144));

        String url = "C:\\Users\\OMEN\\IdeaProjects\\POO_Ariel_Proyecto\\src\\correcto.png";
        int ancho = 50;
        int alto = 50;

        // Crear el ImageIcon con la imagen original
        ImageIcon originalIcon = new ImageIcon(url);

        // Redimensionar la imagen
        Image scaledImage = originalIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        // Crear el ImageIcon con la imagen redimensionada
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Mostrar el mensaje con el ícono
        JOptionPane.showMessageDialog(null,
                "Ejecutacion correcta", // Mensaje
                "Mensaje de validación",   // Título
                JOptionPane.PLAIN_MESSAGE, // Tipo de mensaje
                scaledIcon                 // Icono
        );







    }
    public static void mostrarDialogo2() {
        // Configurar el boton


        UIManager.put("OptionPane.background",new Color(255, 0, 0));


        String url = "C:\\Users\\OMEN\\IdeaProjects\\POO_Ariel_Proyecto\\src\\incorrecto.png";
        int ancho = 50;
        int alto = 50;

        // Crear el ImageIcon con la imagen original
        ImageIcon originalIcon = new ImageIcon(url);

        // Redimensionar la imagen
        Image scaledImage = originalIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

        // Crear el ImageIcon con la imagen redimensionada
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Mostrar el mensaje con el ícono
        JOptionPane.showMessageDialog(null,
                "Ejecutacion incorrecta", // Mensaje
                "Mensaje de validación",   // Título
                JOptionPane.PLAIN_MESSAGE, // Tipo de mensaje
                scaledIcon                 // Icono
        );






    }
}
