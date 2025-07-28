// VetClinicApp.java
// Entry point of the NetBeans-based Java Swing prototype
package vetclinicapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VetClinicApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Vet Clinic Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> performLogin());
        add(loginButton);

        setVisible(true);
    }

    private void performLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.equals("user@example.com") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new HomeFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}

class HomeFrame extends JFrame {
    public HomeFrame() {
        setTitle("Vet Clinic Home");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton bookBtn = new JButton("Book Appointment");
        JButton petsBtn = new JButton("My Pets");
        JButton qrBtn = new JButton("Scan QR");

        bookBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Booking screen placeholder"));
        petsBtn.addActionListener(e -> new MyPetsFrame());
        qrBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "QR Scan simulated!"));

        add(bookBtn);
        add(petsBtn);
        add(qrBtn);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class MyPetsFrame extends JFrame {
    private DefaultListModel<String> petListModel = new DefaultListModel<>();
    private JList<String> petList = new JList<>(petListModel);

    public MyPetsFrame() {
        setTitle("My Pets");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton addPetBtn = new JButton("Add Pet");
        JButton editPetBtn = new JButton("Edit Selected Pet");
        JButton deletePetBtn = new JButton("Delete Selected Pet");

        addPetBtn.addActionListener(e -> addPet());
        editPetBtn.addActionListener(e -> editPet());
        deletePetBtn.addActionListener(e -> deletePet());

        topPanel.add(addPetBtn);
        topPanel.add(editPetBtn);
        topPanel.add(deletePetBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(petList), BorderLayout.CENTER);

        setVisible(true);
    }

    private void addPet() {
        String name = JOptionPane.showInputDialog(this, "Enter pet name:");
        if (name != null && !name.trim().isEmpty()) {
            petListModel.addElement(name.trim());
        }
    }

    private void editPet() {
        int selected = petList.getSelectedIndex();
        if (selected != -1) {
            String newName = JOptionPane.showInputDialog(this, "Edit pet name:", petListModel.getElementAt(selected));
            if (newName != null && !newName.trim().isEmpty()) {
                petListModel.setElementAt(newName.trim(), selected);
            }
        }
    }

    private void deletePet() {
        int selected = petList.getSelectedIndex();
        if (selected != -1) {
            petListModel.remove(selected);
        }
    }
}
