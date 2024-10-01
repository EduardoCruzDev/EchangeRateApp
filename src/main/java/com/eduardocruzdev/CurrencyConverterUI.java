package com.eduardocruzdev;

import com.eduardocruzdev.Service.ExchangeService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class CurrencyConverterUI extends JFrame {

    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField amountField;
    private JTextField customFromField;
    private JTextField customToField;
    private JLabel resultLabel;
    private JButton convertButton;
    private JButton customConvertButton;

    public CurrencyConverterUI() {
        // Configuración inicial de la ventana
        setTitle("Conversor de Monedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);  // Centrar ventana

        // Crear un panel personalizado para dibujar la imagen de fondo
        BackgroundPanel mainPanel = new BackgroundPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Padding entre componentes


        // Configurar el campo de monto
        JLabel amountLabel = new JLabel("Monto:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(amountLabel, gbc);

        amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;  // Hacer que el JTextField se expanda horizontalmente
        mainPanel.add(amountField, gbc);


        // Configurar el ComboBox de moneda 'De'
        JLabel fromLabel = new JLabel("De:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(fromLabel, gbc);

        fromCurrency = new JComboBox<>(new String[]{"USD", "ARS", "BRL", "COP"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;  // Hacer que el JComboBox se expanda horizontalmente
        mainPanel.add(fromCurrency, gbc);

        // Configurar el ComboBox de moneda 'A'
        JLabel toLabel = new JLabel("A:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        mainPanel.add(toLabel, gbc);

        toCurrency = new JComboBox<>(new String[]{"ARS", "USD", "BRL", "COP"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;  // Hacer que el JComboBox se expanda horizontalmente
        mainPanel.add(toCurrency, gbc);



        // Botón de conversión
        convertButton = new JButton("Convertir");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(convertButton, gbc);

        // Resultado de la conversión
        resultLabel = new JLabel("Resultado: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(resultLabel, gbc);

        // Configurar los campos de monedas personalizadas
        JLabel customFromLabel = new JLabel("Moneda Personalizada De:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(customFromLabel, gbc);

        customFromField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        mainPanel.add(customFromField, gbc);

        JLabel customToLabel = new JLabel("Moneda Personalizada A:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(customToLabel, gbc);

        customToField = new JTextField(5);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        mainPanel.add(customToField, gbc);

        // Botón de conversión personalizada
        customConvertButton = new JButton("Convertir Personalizado");
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(customConvertButton, gbc);

        // Agregar el panel principal con imagen de fondo
        add(mainPanel, BorderLayout.CENTER);

        // Acciones del botón de conversión
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConversion();
            }
        });

        // Acciones del botón de conversión personalizada
        customConvertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConversionPersonalizada();
            }
        });



        // Hacer que la ventana sea redimensionable
        setResizable(true);


    }

    private void realizarConversion() {
        try {
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            double result = ExchangeService.convertAmount(from, to, amount);  // Aquí conectas con tu servicio de conversión
            resultLabel.setText(String.format("%.2f %s es %.2f %s", amount, from, result, to));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void realizarConversionPersonalizada() {
        try {
            String from = customFromField.getText().toUpperCase();
            String to = customToField.getText().toUpperCase();
            double amount = Double.parseDouble(amountField.getText());
            double result = ExchangeService.convertAmount(from, to, amount);  // Aquí conectas con tu servicio de conversión
            resultLabel.setText(String.format("%.2f %s es %.2f %s", amount, from, result, to));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Establecer el tema Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear y mostrar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterUI frame = new CurrencyConverterUI();
            frame.setVisible(true);
        });
    }
}

// Clase para el fondo de imagen
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(Main.class.getResource("/images/alura.jpg"))); // Reemplaza con la ruta de tu imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}