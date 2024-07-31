package zoologico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Zoologico {

    public static void main(String[] args) {
        Zoo zoo = new Zoo() {
            @Override
            public double cantar(double num) {
                return 0;
            }
        };

        JFrame frame = new JFrame("Sistema de Zoológico");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton btnAgregarAnimal = new JButton("Agregar Animal");
        JButton btnVerAnimales = new JButton("Ver Animales");
        JButton btnGestionarHabitat = new JButton("Gestionar Hábitat");
        JButton btnAdministrarAlimentos = new JButton("Administrar Alimentos");

        panel.add(btnAgregarAnimal);
        panel.add(btnVerAnimales);
        panel.add(btnGestionarHabitat);
        panel.add(btnAdministrarAlimentos);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Add WindowListener to the frame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Cerrando ventana principal");
            }

            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("Ventana principal abierta");
            }
        });

        // Add MouseListener to buttons
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(Color.LIGHT_GRAY);
                System.out.println("Mouse entered: " + source.getText());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton source = (JButton) e.getSource();
                source.setBackground(UIManager.getColor("Button.background"));
                System.out.println("Mouse exited: " + source.getText());
            }
        };

        btnAgregarAnimal.addMouseListener(mouseListener);
        btnVerAnimales.addMouseListener(mouseListener);
        btnGestionarHabitat.addMouseListener(mouseListener);
        btnAdministrarAlimentos.addMouseListener(mouseListener);

        btnAgregarAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame agregarAnimalFrame = new JFrame("Agregar Animal");
                agregarAnimalFrame.setSize(300, 200);
                agregarAnimalFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                JTextField nombreField = new JTextField(10);
                JTextField habitatField = new JTextField(10);
                JTextField alimentoField = new JTextField(10);

                gbc.gridx = 0;
                gbc.gridy = 0;
                agregarAnimalFrame.add(new JLabel("Nombre:"), gbc);
                gbc.gridx = 1;
                agregarAnimalFrame.add(nombreField, gbc);
                
                gbc.gridx = 0;
                gbc.gridy = 1;
                agregarAnimalFrame.add(new JLabel("Hábitat:"), gbc);
                gbc.gridx = 1;
                agregarAnimalFrame.add(habitatField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                agregarAnimalFrame.add(new JLabel("Alimento:"), gbc);
                gbc.gridx = 1;
                agregarAnimalFrame.add(alimentoField, gbc);

                JButton agregarButton = new JButton("Agregar");
                gbc.gridx = 1;
                gbc.gridy = 3;
                agregarAnimalFrame.add(agregarButton, gbc);

                agregarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombre = nombreField.getText();
                        String habitat = habitatField.getText();
                        String alimento = alimentoField.getText();

                        Animales nuevoAnimal = new Animales(nombre);
                        nuevoAnimal.setHabitat(habitat);
                        nuevoAnimal.setAlimento(alimento);
                        zoo.agregarAnimal(nuevoAnimal);

                        agregarAnimalFrame.dispose();
                    }
                });

                agregarAnimalFrame.setVisible(true);
            }
        });

        btnVerAnimales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame verAnimalesFrame = new JFrame("Ver Animales");
                verAnimalesFrame.setSize(400, 300);
                verAnimalesFrame.setLayout(new BorderLayout());

                JTextArea areaTexto = new JTextArea();
                areaTexto.setEditable(false);

                for (Animales animal : zoo.getAnimales()) {
                    areaTexto.append(animal.toString() + "\n");
                }

                JScrollPane scrollPane = new JScrollPane(areaTexto);
                verAnimalesFrame.add(scrollPane, BorderLayout.CENTER);
                verAnimalesFrame.setVisible(true);
            }
        });

        btnGestionarHabitat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame gestionarHabitatFrame = new JFrame("Gestionar Hábitat");
                gestionarHabitatFrame.setSize(400, 300);
                gestionarHabitatFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);

                JTextField habitatField = new JTextField(20);
                JComboBox<Animales> animalComboBox = new JComboBox<>(zoo.getAnimales().toArray(new Animales[0]));
                JButton asignarHabitatButton = new JButton("Asignar Hábitat");

                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;
                gestionarHabitatFrame.add(new JLabel("Seleccionar Animal:"), gbc);
                gbc.gridx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gestionarHabitatFrame.add(animalComboBox, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.anchor = GridBagConstraints.WEST;
                gestionarHabitatFrame.add(new JLabel("Nuevo hábitat:"), gbc);
                gbc.gridx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gestionarHabitatFrame.add(habitatField, gbc);

                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.NONE;
                gestionarHabitatFrame.add(asignarHabitatButton, gbc);

                asignarHabitatButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Animales selectedAnimal = (Animales) animalComboBox.getSelectedItem();
                        String nuevoHabitat = habitatField.getText();
                        if (selectedAnimal != null) {
                            selectedAnimal.setHabitat(nuevoHabitat);
                        }
                    }
                });

                gestionarHabitatFrame.setVisible(true);
            }
        });

        btnAdministrarAlimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame administrarAlimentosFrame = new JFrame("Administrar Alimentos");
                administrarAlimentosFrame.setSize(400, 300);
                administrarAlimentosFrame.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(5, 5, 5, 5);
        
                JTextField alimentoField = new JTextField(20); 
                JComboBox<Animales> animalComboBox = new JComboBox<>(zoo.getAnimales().toArray(new Animales[0]));
                JButton asignarAlimentoButton = new JButton("Asignar Alimento");
        
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;
                administrarAlimentosFrame.add(new JLabel("Seleccionar Animal:"), gbc);
                gbc.gridx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                administrarAlimentosFrame.add(animalComboBox, gbc);
        
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.anchor = GridBagConstraints.WEST;
                administrarAlimentosFrame.add(new JLabel("Nuevo alimento:"), gbc);
                gbc.gridx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                administrarAlimentosFrame.add(alimentoField, gbc);
        
                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.fill = GridBagConstraints.NONE;
                administrarAlimentosFrame.add(asignarAlimentoButton, gbc);
        
                asignarAlimentoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Animales selectedAnimal = (Animales) animalComboBox.getSelectedItem();
                        String nuevoAlimento = alimentoField.getText();
                        if (selectedAnimal != null) {
                            selectedAnimal.setAlimento(nuevoAlimento);
                        }
                    }
                });
        
                administrarAlimentosFrame.setVisible(true);
            }
        });
        
    }
}
