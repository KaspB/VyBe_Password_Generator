//Password generator v3 created by team VyBe ( Julius Vysniauskas and Kasparas Berenis ) 2024.
//Object-Oriented Programming course.
//MIF VU, Lecturer Irmantas Radavicius.
//Tested on IntelliJ-IDEA-Community 2024.1.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PasswordGenerator extends JFrame
{
    private JButton aZButton;
    private JButton aZButton1;
    private JButton a09Button;
    private JButton $Button;
    private JButton generateButton;
    private JPanel PasswordGeneratorPanel;
    private JTextField textField2;
    private JTextArea textArea1;
    private JButton noLimitButton;
    private JButton btnSave;
    private boolean[] choices = new boolean[5];

    public void setChoices(boolean choice, int index) {
        if(index < 5)
        {
            choices[index]=choice;
        }
            else
            {
                System.out.println("Could not set index for choice variable");
            }
    }

    public boolean[] getChoices()
    {
        return choices;
    }

    public PasswordGenerator()
    {
        setContentPane(PasswordGeneratorPanel);
        setTitle("Password Generator | Team VyBe 2024");
        setSize(600,450);
        setMinimumSize(new Dimension(600, 450));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setChoices(false,0);
        setChoices(false,1);
        setChoices(false,2);
        setChoices(false,3);
        setChoices(false,4);

        aZButton.addActionListener(new ActionListener() {
            static int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count % 2 != 0)
                {
                    aZButton.setBackground(Color.LIGHT_GRAY);
                    setChoices(true, 0);
                }
                    else
                    {
                        aZButton.setBackground(null);
                        setChoices(false, 0);
                    }
                count++;
            }
        });
        aZButton1.addActionListener(new ActionListener() {
            static int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count % 2 != 0)
                {
                    aZButton1.setBackground(Color.LIGHT_GRAY);
                    setChoices(true, 1);
                }
                    else
                    {
                        aZButton1.setBackground(null);
                        setChoices(false, 1);
                    }
                count++;
            }
        });
        a09Button.addActionListener(new ActionListener() {
            static int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count % 2 != 0)
                {
                    a09Button.setBackground(Color.LIGHT_GRAY);
                    setChoices(true, 2);
                }
                    else
                    {
                        a09Button.setBackground(null);
                        setChoices(false, 2);
                    }
                count++;
            }
        });
        $Button.addActionListener(new ActionListener() {
            static int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count % 2 != 0)
                {
                    $Button.setBackground(Color.LIGHT_GRAY);
                    setChoices(true, 3);
                }
                    else
                    {
                        $Button.setBackground(null);
                        setChoices(false, 3);
                    }
                count++;
            }
        });

        noLimitButton.addActionListener(new ActionListener() {
            static int count = 1;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count % 2 != 0)
                {
                    noLimitButton.setBackground(Color.LIGHT_GRAY);
                    setChoices(true, 4);
                }
                else
                {
                    noLimitButton.setBackground(null);
                    setChoices(false, 4);
                }
                count++;
            }
        });

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PasswordLengthDigitFilter filter = new PasswordLengthDigitFilter();
                    String passwordLength = filter.check(textField2.getText());
                    boolean[] checkChoices = getChoices();
                    boolean canGenerate = true;

                    if (!checkChoices[0] && !checkChoices[1] && !checkChoices[2] && !checkChoices[3]) {
                        JOptionPane.showMessageDialog(PasswordGenerator.this,
                                "You must select at least 1 option.", "Try Again", JOptionPane.ERROR_MESSAGE);
                        canGenerate = false;

                    }

                    if (!checkChoices[4]) {
                        if (passwordLength.isEmpty() || Integer.parseInt(passwordLength) < 1 || Integer.parseInt(passwordLength) > 300000) {
                            JOptionPane.showMessageDialog(PasswordGenerator.this,
                                    "The password cannot be empty. Or the length has to be in the range: [ 1 ; 300000 ]", "Try Again", JOptionPane.ERROR_MESSAGE);
                            canGenerate = false;
                        }
                    } else {
                        if (passwordLength.isEmpty() || Integer.parseInt(passwordLength) < 1) {
                            JOptionPane.showMessageDialog(PasswordGenerator.this,
                                    "The password cannot be empty. Or the length has to be more than 1.", "Try Again", JOptionPane.ERROR_MESSAGE);
                            canGenerate = false;
                        }
                    }

                    if (canGenerate) {
                        PasswordAlgorithm algorithm = new PasswordAlgorithm();
                        String generatedPassword = algorithm.generate(checkChoices, Integer.parseInt(passwordLength));
                        textArea1.setText(generatedPassword);
                    }
                } catch (Exception exception)
                {
                    textArea1.setText("An error occurred while generating.");
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String filename = textField2.getText() + "result.txt";
                    Files.writeString(Path.of(filename), textArea1.getText());
                    JOptionPane.showMessageDialog(PasswordGenerator.this,
                            "Saved to " + filename, "Save", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException ex) {
                    textArea1.setText("An error occurred while saving.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args)
    {
        PasswordGenerator mainForm = new PasswordGenerator();
    }
}


